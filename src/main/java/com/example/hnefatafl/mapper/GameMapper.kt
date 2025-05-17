package com.example.hnefatafl.mapper

import com.example.generated.api.model.*
import com.example.hnefatafl.model.MongoGame
import com.example.generated.mongo.Board
import com.example.generated.mongo.Row
import com.example.generated.mongo.Tile
import com.example.generated.mongo.TilePosition
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

    private fun toApiState(mongoState: com.example.generated.mongo.Game.State?): State? {
        return if (mongoState == null) null else State.fromValue(mongoState.value())
    }

    private fun toApiBoard(mongoBoard: com.example.generated.mongo.Board?): com.example.generated.api.model.Board {
        val apiBoard = com.example.generated.api.model.Board()
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

    private fun toApiTilePosition(position: TilePosition?): com.example.generated.api.model.TilePosition? {
        return if (position == null) null else TilePosition(position.x, position.y)
    }

    private fun toApiTile(tile: Tile): com.example.generated.api.model.Tile {
        val newTile = com.example.generated.api.model.Tile()
        newTile.figure = com.example.generated.api.model.Tile.FigureEnum.fromValue(tile.figure.value())
        newTile.isEnabled = tile.isEnabled
        newTile.isMoveEnabled = tile.isMoveEnabled
        newTile.isSelected = tile.isSelected
        return newTile
    }
}