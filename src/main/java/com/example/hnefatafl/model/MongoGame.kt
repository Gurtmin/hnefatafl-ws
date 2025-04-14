package com.example.hnefatafl.model;

import com.example.api.model.ActivePlayer;
import com.example.api.model.PlayerEnum;
import com.example.hnefatafl.context.RequestContext;
import com.example.mongo.model.Player;
import com.example.mongo.model.Players;
import com.example.mongo.model.Tile;
import org.springframework.data.annotation.Id;
import com.example.mongo.model.Game;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoGame extends Game {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void selectTile(Integer x, Integer y) {
        getBoard().getRows().get(y).getCols().get(x).setIsSelected(true);
    }

    public void selectTile(Integer x, Integer y, boolean selected) {
        getBoard().getRows().get(y).getCols().get(x).setIsSelected(selected);
    }

    public Tile.Figure getFigure(Integer x, Integer y) {
        return getBoard().getRows().get(y).getCols().get(x).getFigure();
    }

    public void setFigure(Integer x, Integer y, Tile.Figure figure) {
        getBoard().getRows().get(y).getCols().get(x).setFigure(figure);
    }

    public void setMoveEnabled(Integer x, Integer y, boolean enabled) {
        getBoard().getRows().get(y).getCols().get(x).setIsMoveEnabled(enabled);
    }

    public boolean isCurrentPlayersMoveEnableTile(Tile.Figure movingFigure, Integer x, Integer y) {
        Tile.Figure currentFigure = getFigure(x, y);
        return currentFigure == Tile.Figure.EMPTY || currentFigure == Tile.Figure.EXIT && movingFigure == Tile.Figure.ODIN;
    }

    public void joinMeAsViking() {
        if (getPlayers().getViking() == null)
            getPlayers().setViking(new Player());
        if (getPlayers().getViking().getClientId() == null || getPlayers().getViking().getClientId().isBlank())
            getPlayers().getViking().setClientId(RequestContext.getClientId());
    }

    public void joinMeAsMonster() {
        if (getPlayers().getMonster() == null)
            getPlayers().setMonster(new Player());
        if (getPlayers().getMonster().getClientId() == null || getPlayers().getMonster().getClientId().isBlank())
            getPlayers().getMonster().setClientId(RequestContext.getClientId());
    }

    public void unJoinMeAsViking() {
        if (getPlayers().getViking() == null)
            return;
        if (getPlayers().getViking().getClientId().equals(RequestContext.getClientId()))
            getPlayers().getViking().setClientId( null);
    }

    public void unJoinMeAsMonster() {
        if (getPlayers().getMonster() == null)
            return;
        if (getPlayers().getMonster().getClientId().equals(RequestContext.getClientId()))
            getPlayers().getMonster().setClientId( null);
    }

    public PlayerEnum joined() {
        PlayerEnum result = null;
        if (getPlayers().getMonster() != null &&
            getPlayers().getMonster().getClientId().equals( RequestContext.getClientId())
        ) result = PlayerEnum.MONSTER;

        if (getPlayers().getViking() != null &&
                getPlayers().getViking().getClientId().equals( RequestContext.getClientId())
        ) result = result==PlayerEnum.MONSTER?PlayerEnum.BOTH:PlayerEnum.VIKING;

        return result;
    }
}

//    Hraje se na hrací desce 11×11 polí (nebo větší 13×13) s vyznačeným středovým polem nazývaným hrad či trůn, s 12 bílými kameny a králem pro obránce a 24 černými kameny pro útočníka. Útočníkovým cílem hry je zajmout krále, obráncovým uprchnout s králem do rohu hrací desky. Hráči se v tazích pravidelně střídají.
//
//    Pravidla
//    Všechny kameny mohou táhnout jako věž v šachu - o libovolný počet volných polí nahoru, dolů, doleva a doprava, dokud nenarazí na překážku.
//    Na pole ve středu a rohy hracího plánu smí vstoupit jen král, ostatní kameny mohou ve svém tahu středové pole jen přeskočit.
//            Každý kámen (kromě krále) je zajat, pokud je ze dvou protilehlých stran těsně sevřen nepřátelskými kameny tak, že se nemůže hýbat.
//    Pokud kámen do této pozice vstoupí, zajat není.
//    Jedním tahem je možné zajmout i více soupeřových kamenů.
//    K zajetí lze využít i zvýrazněná pole (rohy a středové pole bez krále), která tak zastupují jeden obkličující kámen.
//    Král je zajat, pokud je obklíčen ze všech čtyř stran, nebo ze tří stran a trůnem.
//    Král může zajímat stejným způsobem jako ostatní kameny.
