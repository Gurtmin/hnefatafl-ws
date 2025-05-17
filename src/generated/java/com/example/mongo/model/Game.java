
package com.example.mongo.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * Game
 * <p>
 * 
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "type",
    "players",
    "state",
    "board"
})
@Generated("jsonschema2pojo")
public class Game {

    @JsonProperty("id")
    private String id;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    private String type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("players")
    private Players players;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("state")
    private Game.State state;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("board")
    private Board board;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("players")
    public Players getPlayers() {
        return players;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("players")
    public void setPlayers(Players players) {
        this.players = players;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("state")
    public Game.State getState() {
        return state;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("state")
    public void setState(Game.State state) {
        this.state = state;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("board")
    public Board getBoard() {
        return board;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("board")
    public void setBoard(Board board) {
        this.board = board;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Game.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("players");
        sb.append('=');
        sb.append(((this.players == null)?"<null>":this.players));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("board");
        sb.append('=');
        sb.append(((this.board == null)?"<null>":this.board));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.players == null)? 0 :this.players.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.board == null)? 0 :this.board.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Game) == false) {
            return false;
        }
        Game rhs = ((Game) other);
        return (((((((this.players == rhs.players)||((this.players!= null)&&this.players.equals(rhs.players)))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.board == rhs.board)||((this.board!= null)&&this.board.equals(rhs.board))));
    }

    @Generated("jsonschema2pojo")
    public enum State {

        OPEN("Open"),
        STARTED("Started"),
        OVER("Over");
        private final String value;
        private final static Map<String, Game.State> CONSTANTS = new HashMap<String, Game.State>();

        static {
            for (Game.State c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        State(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Game.State fromValue(String value) {
            Game.State constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
