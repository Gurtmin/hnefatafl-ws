package com.example.hnefatafl.service

import com.example.api.model.Game
import com.example.api.model.JoinPlayerRequest
import com.example.api.model.PagedGameResponse
import com.example.api.model.PlayerEnum
import com.example.hnefatafl.context.RequestContext
import com.example.hnefatafl.exception.ObjectAlreadyExistsException
import com.example.hnefatafl.exception.ObjectNotFoundException
import com.example.hnefatafl.mapper.GameMapper.toApiGame
import com.example.hnefatafl.model.MongoGame
import com.example.hnefatafl.repository.GameRepository
import com.example.mongo.model.*
import com.example.mongo.model.Tile.Figure
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class GameService(private val gameRepository: GameRepository) {
    fun createNewGame(type: String): MongoGame {
        if (type.isBlank() || !gameRepository.findByType(type).isEmpty()) throw ObjectAlreadyExistsException(type)
        val mongoGame = MongoGame()
        mongoGame.type = type
        mongoGame.board = newBoard
        mongoGame.players = Players()
        mongoGame.players.active = Players.Active.VIKING
        mongoGame.players.viking = Player()
        mongoGame.players.monster = Player()
        mongoGame.joinMeAsViking()
        mongoGame.unJoinMeAsMonster()
        return gameRepository.save(mongoGame)
    }

    fun join(id: String, request: JoinPlayerRequest): PlayerEnum? {
        val game = gameRepository.findById(id)
            .orElseThrow { ObjectNotFoundException(id) }
        if (request.player == null) {
            game.unJoinMeAsViking()
            game.unJoinMeAsMonster()
            gameRepository.save(game)
            return game.joined()
        }
        when (request.player) {
            PlayerEnum.NONE -> {
                game.unJoinMeAsViking()
                game.unJoinMeAsMonster()
            }

            PlayerEnum.VIKING -> {
                game.joinMeAsViking()
                game.unJoinMeAsMonster()
            }

            PlayerEnum.MONSTER -> {
                game.joinMeAsMonster()
                game.unJoinMeAsViking()
            }

            PlayerEnum.BOTH -> {
                game.joinMeAsMonster()
                game.joinMeAsViking()
            }
        }
        gameRepository.save(game)
        return game.joined()
    }

    private val newBoard: Board
        private get() {
            val newBoard = Board()
            for (rowIndex in 0..10) {
                val newRow = Row()
                for (colIndex in 0..10) {
                    newRow.cols.add(getTileByOrigin(rowIndex, colIndex))
                }
                newBoard.rows.add(newRow)
            }
            return newBoard
        }

    private fun getTileByOrigin(row: Int, col: Int): Tile {
        val newTile = Tile()
        if (row == 0 && col == 0 || row == 0 && col == 10 || row == 10 && col == 0 || row == 10 && col == 10) newTile.figure =
            Figure.EXIT else if (row in 3..7 && col in setOf(0,10) || col in 3..7 && row in setOf(0,10) || row == 5 && col in setOf(1,9) || col == 5 && row in setOf(1,9)) newTile.figure =
            Figure.WARRIOR else if (col == 5 && row == 5) newTile.figure =
            Figure.ODIN else if (row in 4..6 && col in 4 .. 6 || row == 5 && col in setOf(3,7) || col == 5 && row in setOf(3,7)) newTile.figure =
            Figure.MONSTER else newTile.figure = Figure.EMPTY
        return newTile
    }

    fun getAllGames(pageable: Pageable?): PagedGameResponse {
        val gamePage = gameRepository.findAll(pageable)
        val result = gamePage.content.stream()
            .map { game: MongoGame -> toApiGame(game) }
            .collect(Collectors.toList())
        return PagedGameResponse(
            result,
            Integer.valueOf(gamePage.number),
            Integer.valueOf(gamePage.size),
            Integer.valueOf(gamePage.totalPages)
        )
    }

    fun changeGameName(id: String, type: String?): MongoGame {
        return gameRepository.findById(id)
            .map { game: MongoGame ->
                game.type = type
                gameRepository.save(game)
            }
            .orElseThrow { ObjectNotFoundException(type) }
    }

    fun getGameById(id: String): Game {
        val game = gameRepository.findById(id)
            .orElseThrow { ObjectNotFoundException(id) }
        return toApiGame(game)
    }

    fun selectTile(gameId: String, x: Int, y: Int): Game {
        val game = gameRepository.findById(gameId)
            .orElseThrow { ObjectNotFoundException(gameId) }

        val selectedPosition = game.getSelectedPosition()
        if( selectedPosition != null)
            moveTile(game, selectedPosition, x, y)

        if (!game.canPlayerAct(RequestContext.getClientId())) return toApiGame(game)
        game.clearTileSelection()
        if (x != null && x < 11 && y != null && y < 11 &&
            isFigureSelectable(game.players.active, game.getFigure(x, y))
        ) {
            game.selectTile(x, y)
            countEnabledTiles(game, x, y)
        }
        gameRepository.save(game)
        return toApiGame(game)
    }

    fun moveTile(game: MongoGame, selectedModel: TilePosition, x: Int, y: Int): Game {
        val moveTo = TilePosition().apply { setX(x); setY(y) }

        if (!game.canPlayerAct(RequestContext.getClientId()))
            return toApiGame(game)

        if(!game.board.rows[moveTo.y].cols[moveTo.x].isMoveEnabled)
            return toApiGame(game)

        game.setFigure( moveTo,game.getFigure(selectedModel))
        if( selectedModel.x == 5 && selectedModel.y == 5)
            game.setFigure( selectedModel,Figure.THRONE)
        else
            game.setFigure( selectedModel,Figure.EMPTY)
        game.clearTileSelection()

        if( game.getFigure(moveTo)==Figure.ODIN && (moveTo.x==0 && moveTo.y==0 || moveTo.x==0 && moveTo.y==10 || moveTo.x==10 && moveTo.y==10 || moveTo.x==10 && moveTo.y==0)) {
            game.setFigure(moveTo, Figure.ESCAPED_ODIN)
            game.state = com.example.mongo.model.Game.State.OVER
        }

        game.removeTile( moveTo);

        // ze by se melo overit vyhozeni figury

        game.switchPlayers()

        gameRepository.save(game)
        return toApiGame(game)
    }

    private fun isFigureSelectable(activePlayer: Players.Active, currentFigure: Figure): Boolean {
        return (activePlayer == Players.Active.MONSTER && EnumSet.of(Figure.ODIN, Figure.MONSTER)
            .contains(currentFigure)
                ||
                activePlayer == Players.Active.VIKING && EnumSet.of(Figure.WARRIOR).contains(currentFigure))
    }

    private fun countEnabledTiles(game: MongoGame, x: Int, y: Int) {
        val selectedFigure = game.getFigure(x, y)
        for (rowIndex in y + 1 until game.board.rows.size) {
            if(game.getFigure(x, rowIndex) == Figure.THRONE) continue
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, x, rowIndex)) game.setMoveEnabled(
                x,
                rowIndex,
                true
            ) else
            break
        }
        for (rowIndex in y - 1 downTo 0) {
            if(game.getFigure(x, rowIndex) == Figure.THRONE) continue
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, x, rowIndex)) game.setMoveEnabled(
                x,
                rowIndex,
                true
            ) else break
        }
        for (colIndex in x + 1 until game.board.rows[y].cols.size) {
            if(game.getFigure(colIndex, y) == Figure.THRONE) continue
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, colIndex, y)) game.setMoveEnabled(
                colIndex,
                y,
                true
            ) else break
        }
        for (colIndex in x - 1 downTo 0) {
            if(game.getFigure(colIndex, y) == Figure.THRONE) continue
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, colIndex, y)) game.setMoveEnabled(
                colIndex,
                y,
                true
            ) else break
        }
    }
}