package com.example.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.api.model.ActivePlayer;
import com.example.api.model.Player;
import com.example.api.model.PlayerEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * GamePlayers
 */

@JsonTypeName("Game_players")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class GamePlayers {

  private PlayerEnum me;

  private ActivePlayer active;

  private Player viking;

  private Player monster;

  public GamePlayers() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public GamePlayers(ActivePlayer active) {
    this.active = active;
  }

  public GamePlayers me(PlayerEnum me) {
    this.me = me;
    return this;
  }

  /**
   * Get me
   * @return me
  */
  @Valid 
  @Schema(name = "me", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("me")
  public PlayerEnum getMe() {
    return me;
  }

  public void setMe(PlayerEnum me) {
    this.me = me;
  }

  public GamePlayers active(ActivePlayer active) {
    this.active = active;
    return this;
  }

  /**
   * Get active
   * @return active
  */
  @NotNull @Valid 
  @Schema(name = "active", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("active")
  public ActivePlayer getActive() {
    return active;
  }

  public void setActive(ActivePlayer active) {
    this.active = active;
  }

  public GamePlayers viking(Player viking) {
    this.viking = viking;
    return this;
  }

  /**
   * Get viking
   * @return viking
  */
  @Valid 
  @Schema(name = "viking", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("viking")
  public Player getViking() {
    return viking;
  }

  public void setViking(Player viking) {
    this.viking = viking;
  }

  public GamePlayers monster(Player monster) {
    this.monster = monster;
    return this;
  }

  /**
   * Get monster
   * @return monster
  */
  @Valid 
  @Schema(name = "monster", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("monster")
  public Player getMonster() {
    return monster;
  }

  public void setMonster(Player monster) {
    this.monster = monster;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GamePlayers gamePlayers = (GamePlayers) o;
    return Objects.equals(this.me, gamePlayers.me) &&
        Objects.equals(this.active, gamePlayers.active) &&
        Objects.equals(this.viking, gamePlayers.viking) &&
        Objects.equals(this.monster, gamePlayers.monster);
  }

  @Override
  public int hashCode() {
    return Objects.hash(me, active, viking, monster);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GamePlayers {\n");
    sb.append("    me: ").append(toIndentedString(me)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    viking: ").append(toIndentedString(viking)).append("\n");
    sb.append("    monster: ").append(toIndentedString(monster)).append("\n");
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

