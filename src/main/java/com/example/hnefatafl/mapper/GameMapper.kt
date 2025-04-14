package com.example.hnefatafl.mapper

import com.example.api.model.*
import com.example.hnefatafl.model.MongoGame
import com.example.mongo.model.Board
import com.example.mongo.model.Row
import com.example.mongo.model.Tile
import com.example.mongo.model.TilePosition
import java.util.function.Consumer

object GameMapper {
    @JvmStatic
    fun toApiGame(mongoGame: MongoGame): Game {
        val apiGame = Game()
        apiGame.id = mongoGame.id
        apiGame.type = mongoGame.type
        apiGame.state = toApiState(mongoGame.state)
        apiGame.board = toApiBoard(mongoGame.board)
        apiGame.players = GamePlayers(ActivePlayer.fromValue(mongoGame.players.active.value()))
//        if (mongoGame.players.monster != null)
            apiGame.players.monster = Player(mongoGame.players.monster.name)
//        if (mongoGame.players.viking != null)
            apiGame.players.viking = Player(mongoGame.players.viking.name)
        apiGame.players.me = mongoGame.joined()
        return apiGame
    }

    private fun toApiState(mongoState: com.example.mongo.model.Game.State?): State? {
        return if (mongoState == null) null else State.fromValue(mongoState.value())
    }

    fun toApiBoard(mongoBoard: Board?): com.example.api.model.Board {
        val apiBoard = com.example.api.model.Board()
        if (mongoBoard != null) {
            mongoBoard.rows.forEach(Consumer { row: Row ->
                val newRow = BoardRowsInner()
                row.cols.forEach(Consumer { col: Tile -> newRow.addColsItem(toApiTile(col)) })
                apiBoard.addRowsItem(newRow)
            })
            apiBoard.selectedTile = toApiTilePosition(mongoBoard.selectedTile)
        }
        return apiBoard
    }

    private fun toApiTilePosition(position: TilePosition?): com.example.api.model.TilePosition? {
        return if (position == null) null else TilePosition(position.x, position.y)
    }

    private fun toApiTile(tile: Tile): com.example.api.model.Tile {
        val newTile = com.example.api.model.Tile()
        newTile.figure = com.example.api.model.Tile.FigureEnum.fromValue(tile.figure.value())
        newTile.isEnabled = tile.isEnabled
        newTile.isMoveEnabled = tile.isMoveEnabled
        newTile.isSelected = tile.isSelected
        return newTile
    }
}