package com.example.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.api.model.PlayerEnum;
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
 * JoinPlayerRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class JoinPlayerRequest {

  private PlayerEnum player;

  public JoinPlayerRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public JoinPlayerRequest(PlayerEnum player) {
    this.player = player;
  }

  public JoinPlayerRequest player(PlayerEnum player) {
    this.player = player;
    return this;
  }

  /**
   * Get player
   * @return player
  */
  @NotNull @Valid 
  @Schema(name = "player", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("player")
  public PlayerEnum getPlayer() {
    return player;
  }

  public void setPlayer(PlayerEnum player) {
    this.player = player;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JoinPlayerRequest joinPlayerRequest = (JoinPlayerRequest) o;
    return Objects.equals(this.player, joinPlayerRequest.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(player);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JoinPlayerRequest {\n");
    sb.append("    player: ").append(toIndentedString(player)).append("\n");
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

