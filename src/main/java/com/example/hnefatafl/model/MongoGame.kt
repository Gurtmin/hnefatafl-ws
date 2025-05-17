package com.example.hnefatafl.model

import com.example.generated.api.model.ActivePlayer
import com.example.generated.api.model.PlayerEnum
import com.example.hnefatafl.context.RequestContext
import com.example.generated.mongo.Game
import com.example.generated.mongo.Players
import com.example.generated.mongo.Tile.Figure
import com.example.generated.mongo.TilePosition
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

val friendlyFigures = mapOf(
    Players.Active.VIKING to setOf(Figure.WARRIOR, Figure.EXIT),
    Players.Active.MONSTER to setOf(Figure.MONSTER, Figure.ODIN, Figure.EXIT)
)
val enemyFigures = mapOf(
    Players.Active.VIKING to setOf(Figure.MONSTER, Figure.ODIN),
    Players.Active.MONSTER to setOf(Figure.WARRIOR)
)
val neededOverwhelmCount = mapOf(
    Figure.ODIN to 2,
    Figure.MONSTER to 1,
    Figure.WARRIOR to 1,
)

@Document
class MongoGame : Game() {
    @Id
    private var id: String? = null
    override fun getId(): String {
        return id!!
    }

    override fun setId(id: String) {
        this.id = id
    }

    fun selectTile(x: Int?, y: Int?, selected: Boolean = true) {
        board.rows[y!!].cols[x!!].isSelected = selected
    }

    fun getFigure(x: Int, y: Int): Figure {
        if(x>10 || y>10 || x<0 || y<0)
            return Figure.EMPTY
        return board.rows[y].cols[x].figure
    }

    fun getFigure( position : TilePosition): Figure {
        return getFigure(position.x, position.y)
    }

    fun setFigure( x:Int, y:Int, figure: Figure) {
        board.rows[y].cols[x].figure = figure
    }

    fun setFigure( position: TilePosition, figure: Figure) {
        setFigure( position.x, position.y, figure)
    }

    fun setMoveEnabled(x: Int?, y: Int?, enabled: Boolean) {
        board.rows[y!!].cols[x!!].isMoveEnabled = enabled
    }

    fun isCurrentPlayersMoveEnableTile(movingFigure: Figure, x: Int, y: Int): Boolean {
        val currentFigure = getFigure(x, y)
        return currentFigure == Figure.EMPTY || currentFigure == Figure.EXIT && movingFigure == Figure.ODIN
    }

    fun joinMeAsViking() {
        if ((players?.viking?.clientId?:"").isBlank())
            players.viking.clientId = RequestContext.clientId
    }

    fun joinMeAsMonster() {
        if ((players?.monster?.clientId?:"").isBlank())
            players.monster.clientId = RequestContext.clientId
    }

    fun unJoinMeAsViking() {
        if (players?.viking?.clientId == RequestContext.clientId)
            players.viking.clientId = null
    }

    fun unJoinMeAsMonster() {
        if (players?.monster?.clientId == RequestContext.clientId)
            players.monster.clientId = null
    }

    fun joined(): PlayerEnum? {
        var result: PlayerEnum? = null
        if (players?.monster?.clientId == RequestContext.clientId)
            result = PlayerEnum.MONSTER
        if (players?.viking?.clientId == RequestContext.clientId)
            result =
                if (result == PlayerEnum.MONSTER) PlayerEnum.BOTH else PlayerEnum.VIKING
        return result
    }

    fun canPlayerAct(clientId: String): Boolean {
        if( players?.active==Players.Active.VIKING)
            return players?.viking?.clientId==clientId;
        if( players?.active==Players.Active.MONSTER)
            return players?.monster?.clientId==clientId;

        return false;
    }

    fun getSelectedPosition(): TilePosition? {
        for ((y, row) in board.rows.withIndex()){
            for((x, tile) in row.cols.withIndex()){
                if(tile.isSelected == true) {
                    return TilePosition().apply { this.x=x; this.y=y }
                }
            }
        }
        return null
    }

    public fun clearTileSelection() {
        for (row in board.rows) {
            for (tile in row.cols) {
                tile.isSelected = false
                tile.isMoveEnabled = false
                tile.isEnabled = false
            }
        }
    }

    fun switchPlayers() {
        players.active = if(players.active==Players.Active.MONSTER)
            Players.Active.VIKING
        else
            Players.Active.MONSTER
    }

    fun removeVictim(x: Int, y: Int, activePlayer: Players.Active) {
        if(x<0 || x>10 || y<0 || y>10)
            return;

        val ff = friendlyFigures[activePlayer]
        val ef = enemyFigures[activePlayer]

        if( ef==null || ff==null)
            return

        removeVictim(x, y, ff, ef)
    }

    private fun removeVictim(x: Int, y: Int, friendlyFigures: Set<Figure>, enemyFigures: Set<Figure>) {
        val victimFigure = getFigure( x,y)
        if(!enemyFigures.contains( victimFigure))
            return

        var overwhelmCount =
            if(
                friendlyFigures.contains( getFigure( x+1,y)) &&
                friendlyFigures.contains( getFigure( x-1,y))
                ) 1 else 0;

        overwhelmCount = if(
                friendlyFigures.contains( getFigure( x,y-1)) &&
                friendlyFigures.contains( getFigure( x,y+1))
            ) overwhelmCount + 1 else overwhelmCount

        if( overwhelmCount >= (neededOverwhelmCount[victimFigure]?:0)){
            if(victimFigure==Figure.ODIN) {
                state = State.OVER
                setFigure(x, y, Figure.THRONE)
            }
            else
                setFigure(x, y, Figure.EMPTY)
        }
    }

    fun removeTile(newPosition: TilePosition) {
        removeVictim( newPosition.x-1, newPosition.y, players.active)
        removeVictim( newPosition.x+1, newPosition.y, players.active)
        removeVictim( newPosition.x, newPosition.y-1, players.active)
        removeVictim( newPosition.x, newPosition.y+1, players.active)
    }
}
