package com.example.hnefatafl.service;

import com.example.api.model.Game;
import com.example.api.model.PagedGameResponse;
import com.example.hnefatafl.exception.ObjectNotFoundException;
import com.example.hnefatafl.mapper.GameMapper;
import com.example.hnefatafl.model.MongoGame;
import com.example.hnefatafl.repository.GameRepository;
import com.example.mongo.model.Board;
import com.example.mongo.model.Row;
import com.example.mongo.model.Tile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository)
    {
        this.gameRepository = gameRepository;
    }

    public MongoGame createNewGame(String type) {
        if( gameRepository.findById(type).isEmpty())
            throw new ObjectNotFoundException(type);

        MongoGame mongoGame = new MongoGame();
        mongoGame.setType(type);
        mongoGame.setBoard( getNewBoard());

        return gameRepository.save(mongoGame);
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
        if( (row==0 && col==0) || (row==0 && col==10) || (row==10 && col==0) || (row==10 && col==10))
            return Tile.EXIT;
        if(
            (row>2 && row<8 && (col==0 || col==10)) ||
            (col>2 && col<8 && (row==0 || row==10)) ||
            ((row==5) && (col==1 || col==9)) ||
            ((col==5) && (row==1 || row==9))
        )
            return Tile.WARRIOR;
        if(col==5 && row==5)
            return Tile.ODIN;
        if(
             (row>3 && row<7 && col>3 && col<7) ||
             ((row==5) && (col==3 || col==7)) ||
             ((col==5) && (row==3 || row==7))
        )
            return Tile.MONSTER;
        return Tile.EMPTY;
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
                Integer.valueOf( result.size())
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
        return GameMapper.toApiGame(
                gameRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(id)
                        )
        );
    }
}
