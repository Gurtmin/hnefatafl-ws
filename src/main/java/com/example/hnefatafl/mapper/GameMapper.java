package com.example.hnefatafl.mapper;

import com.example.api.model.*;
import com.example.hnefatafl.model.MongoGame;

public class GameMapper {
    public static Game toApiGame(MongoGame mongoGame) {
        Game apiGame = new Game();
        apiGame.setId(mongoGame.getId());
        apiGame.setType(mongoGame.getType());

        apiGame.setState( toApiState(mongoGame.getState()));
        apiGame.setActivePlayer( toApiActivePlayer( mongoGame.getActivePlayer()));

        apiGame.setPlayer1(toApiPlayer(mongoGame.getPlayer1()));
        apiGame.setPlayer2(toApiPlayer(mongoGame.getPlayer2()));
        apiGame.setBoard(toApiBoard(mongoGame.getBoard()));

        return apiGame;
    }

    private static Player toApiPlayer(com.example.mongo.model.Player mongoPlayer) {
        if(mongoPlayer==null)
            return null;
        return new Player(mongoPlayer.toString());
    }

    private static State toApiState(com.example.mongo.model.Game.State mongoState){
        if(mongoState==null)
            return null;
        return State.fromValue( mongoState.value());
    }
    private static ActivePlayer toApiActivePlayer(com.example.mongo.model.Game.ActivePlayer activePlayer){
        if(activePlayer==null)
            return null;
        return ActivePlayer.fromValue( activePlayer.value());
    }

    private static Board toApiBoard(com.example.mongo.model.Board mongoBoard) {
        Board apiBoard = new Board();
        if(mongoBoard!=null) {
            mongoBoard.getRows().forEach(row -> {
                BoardRowsInner newRow = new BoardRowsInner();
                row.getCols().forEach(col -> {
                    newRow.addColsItem(toApiTile(col));
                });
                apiBoard.addRowsItem(newRow);
            });
            apiBoard.setSelectedTile( toApiTilePosition(mongoBoard.getSelectedTile()));
        }

        return apiBoard;
    }

    private static TilePosition toApiTilePosition(com.example.mongo.model.TilePosition position) {
        if(position==null)
            return null;
        return new TilePosition(position.getX(),position.getY());
    }

    private static Tile toApiTile(com.example.mongo.model.Tile tile) {
        return Tile.fromValue( tile.value());
    }
}
