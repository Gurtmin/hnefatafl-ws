package com.example.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.generated.api.model.Board;
import com.example.generated.api.model.GamePlayers;
import com.example.generated.api.model.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Game
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T16:58:09.650237800+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class Game {

  private String id;

  private String type;

  private State state;

  private GamePlayers players;

  private Board board;

  public Game() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Game(String id, String type, State state, GamePlayers players, Board board) {
    this.id = id;
    this.type = type;
    this.state = state;
    this.players = players;
    this.board = board;
  }

  public Game id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Game type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  */
  @NotNull 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Game state(State state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @NotNull @Valid 
  @Schema(name = "state", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("state")
  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public Game players(GamePlayers players) {
    this.players = players;
    return this;
  }

  /**
   * Get players
   * @return players
  */
  @NotNull @Valid 
  @Schema(name = "players", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("players")
  public GamePlayers getPlayers() {
    return players;
  }

  public void setPlayers(GamePlayers players) {
    this.players = players;
  }

  public Game board(Board board) {
    this.board = board;
    return this;
  }

  /**
   * Get board
   * @return board
  */
  @NotNull @Valid 
  @Schema(name = "board", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("board")
  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(this.id, game.id) &&
        Objects.equals(this.type, game.type) &&
        Objects.equals(this.state, game.state) &&
        Objects.equals(this.players, game.players) &&
        Objects.equals(this.board, game.board);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, state, players, board);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    players: ").append(toIndentedString(players)).append("\n");
    sb.append("    board: ").append(toIndentedString(board)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

