package com.example.hnefatafl.service;

import com.example.api.model.*;
import com.example.api.model.Game;
import com.example.hnefatafl.context.RequestContext;
import com.example.hnefatafl.exception.ObjectAlreadyExistsException;
import com.example.hnefatafl.exception.ObjectNotFoundException;
import com.example.hnefatafl.mapper.GameMapper;
import com.example.hnefatafl.model.MongoGame;
import com.example.hnefatafl.repository.GameRepository;
import com.example.mongo.model.*;
import com.example.mongo.model.Board;
import com.example.mongo.model.Player;
import com.example.mongo.model.Tile;
import com.example.mongo.model.Tile.Figure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public MongoGame createNewGame(String type) {
        if( type.isBlank() || !gameRepository.findByType(type).isEmpty())
            throw new ObjectAlreadyExistsException(type);

        MongoGame mongoGame = new MongoGame();
        mongoGame.setType(type);
        mongoGame.setBoard( getNewBoard());
        mongoGame.setPlayers( new Players());
        mongoGame.getPlayers().setActive(Players.Active.VIKING);
        mongoGame.getPlayers().setViking(new Player());
        mongoGame.getPlayers().setMonster(new Player());
        mongoGame.joinMeAsViking();
        mongoGame.unJoinMeAsMonster();

        return gameRepository.save(mongoGame);
    }

    public PlayerEnum join( String id, JoinPlayerRequest request){
        MongoGame game = gameRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id));

        if(request.getPlayer()==null){
            game.unJoinMeAsViking();
            game.unJoinMeAsMonster();
            gameRepository.save(game);
            return game.joined();
        }

        switch (request.getPlayer()){
            case VIKING ->{
                game.joinMeAsViking();
                game.unJoinMeAsMonster();
            }
            case MONSTER -> {
                game.joinMeAsMonster();
                game.unJoinMeAsViking();
            }
            case BOTH -> {
                game.joinMeAsMonster();
                game.joinMeAsViking();
            }
        }

        gameRepository.save(game);
        return game.joined();
    }

    private Board getNewBoard() {
        Board newBoard = new Board();
        for(int rowIndex = 0; rowIndex<11; rowIndex++) {
            Row newRow = new Row();
            for (int colIndex = 0; colIndex < 11; colIndex++) {
                newRow.getCols().add(getTileByOrigin(rowIndex,colIndex));
            }
            newBoard.getRows().add(newRow);
        }
        return newBoard;
    }

    private Tile getTileByOrigin(int row, int col) {
        Tile newTile = new Tile();

        if( (row==0 && col==0) || (row==0 && col==10) || (row==10 && col==0) || (row==10 && col==10))
            newTile.setFigure( Tile.Figure.EXIT);
        else if(
            (row>2 && row<8 && (col==0 || col==10)) ||
            (col>2 && col<8 && (row==0 || row==10)) ||
            ((row==5) && (col==1 || col==9)) ||
            ((col==5) && (row==1 || row==9))
        )
            newTile.setFigure( Tile.Figure.WARRIOR);
        else if(col==5 && row==5)
            newTile.setFigure( Tile.Figure.ODIN);
        else if(
             (row>3 && row<7 && col>3 && col<7) ||
             ((row==5) && (col==3 || col==7)) ||
             ((col==5) && (row==3 || row==7))
        )
            newTile.setFigure( Tile.Figure.MONSTER);
        else newTile.setFigure( Tile.Figure.EMPTY);

        return newTile;
    }

    public PagedGameResponse getAllGames(Pageable pageable) {
        Page<MongoGame> gamePage = gameRepository.findAll( pageable);

        List<Game> result = gamePage.getContent().stream()
                .map(GameMapper::toApiGame)
                .collect(Collectors.toList());

        return new PagedGameResponse(
                result,
                Integer.valueOf( gamePage.getNumber()),
                Integer.valueOf( gamePage.getSize()),
                Integer.valueOf( gamePage.getTotalPages())
        );
    }

    public MongoGame changeGameName(String id, String type) {
        return gameRepository.findById(id)
            .map(game -> {
                game.setType(type);
                return gameRepository.save(game);
            })
            .orElseThrow(() -> new ObjectNotFoundException(type));
    }

    public Game getGameById(String id) {
        MongoGame game = gameRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(id));
        Game response = GameMapper.toApiGame(game);

        return response;
    }

    public com.example.api.model.Game selectTile(String gameId, Integer x, Integer y) {
        MongoGame game = gameRepository.findById(gameId)
            .orElseThrow(() -> new ObjectNotFoundException(gameId));
        clearTileSelection(game);

        if(
                x!=null && x<11 && y!=null && y<11
                &&
                isFigureSelectable( game.getPlayers().getActive(), game.getFigure(x,y))
        ) {
            game.selectTile(x, y);
            countEnabledTiles( game, x, y);
        }
        gameRepository.save(game);

        return GameMapper.toApiGame( game);
    }

    private boolean isFigureSelectable(Players.Active activePlayer, Figure currentFigure) {
        return
            activePlayer == Players.Active.MONSTER && EnumSet.of( Figure.ODIN,Figure.MONSTER).contains(currentFigure)
            ||
            activePlayer == Players.Active.VIKING && EnumSet.of( Figure.WARRIOR).contains(currentFigure)
        ;
    }

    private void clearTileSelection(MongoGame game) {
        for (Row row:game.getBoard().getRows()){
            for (Tile tile:row.getCols()){
                tile.setIsSelected( false);
                tile.setIsMoveEnabled( false);
                tile.setIsEnabled( false);
            }
        }
    }

    private void countEnabledTiles(MongoGame game, Integer x, Integer y) {
        Tile.Figure selectedFigure = game.getFigure(x, y);
        for( Integer rowIndex = y+1; rowIndex < game.getBoard().getRows().size(); rowIndex++) {
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, x, rowIndex))
                game.setMoveEnabled(x, rowIndex, true);
            else
                break;
        }
        for( Integer rowIndex = y-1; rowIndex >= 0; rowIndex--) {
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, x, rowIndex))
                game.setMoveEnabled(x,rowIndex,true);
            else
                break;
        }
        for( Integer colIndex = x+1; colIndex < game.getBoard().getRows().get(y).getCols().size(); colIndex++) {
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, colIndex,y))
                game.setMoveEnabled(colIndex,y,true);
            else
                break;
        }
        for( Integer colIndex = x-1; colIndex >= 0; colIndex--) {
            if (game.isCurrentPlayersMoveEnableTile(selectedFigure, colIndex,y))
                game.setMoveEnabled(colIndex,y,true);
            else
                break;
        }
    }

    private void moveTile(MongoGame game, Integer x, Integer y) {
    }
}
