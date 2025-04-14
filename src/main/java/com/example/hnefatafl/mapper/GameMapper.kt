package com.example.hnefatafl.mapper;

import com.example.api.model.*;
import com.example.hnefatafl.model.MongoGame;

public class GameMapper {
    public static Game toApiGame(MongoGame mongoGame) {
        Game apiGame = new Game();
        apiGame.setId(mongoGame.getId());
        apiGame.setType(mongoGame.getType());
        apiGame.setState( toApiState(mongoGame.getState()));
        apiGame.setBoard(toApiBoard(mongoGame.getBoard()));

        apiGame.setPlayers( new GamePlayers( ActivePlayer.fromValue( mongoGame.getPlayers().getActive().value())));
        if(mongoGame.getPlayers().getMonster()!=null)
            apiGame.getPlayers().setMonster( new Player( mongoGame.getPlayers().getMonster().getName()));
        if(mongoGame.getPlayers().getViking()!=null)
            apiGame.getPlayers().setViking( new Player( mongoGame.getPlayers().getViking().getName()));
        apiGame.getPlayers().setMe( mongoGame.joined());

        return apiGame;
    }

    private static State toApiState(com.example.mongo.model.Game.State mongoState){
        if(mongoState==null)
            return null;
        return State.fromValue( mongoState.value());
    }

    public static Board toApiBoard(com.example.mongo.model.Board mongoBoard) {
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
        Tile newTile = new Tile();
        newTile.setFigure( Tile.FigureEnum.fromValue(tile.getFigure().value()));
        newTile.setIsEnabled(tile.getIsEnabled());
        newTile.setIsMoveEnabled(tile.getIsMoveEnabled());
        newTile.setIsSelected(tile.getIsSelected());
        return newTile;
    }
}
