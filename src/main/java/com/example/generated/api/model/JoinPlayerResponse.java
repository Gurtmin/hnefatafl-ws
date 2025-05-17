package com.example.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.generated.api.model.PlayerEnum;
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
 * JoinPlayerResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T16:58:09.650237800+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class JoinPlayerResponse {

  private PlayerEnum player;

  public JoinPlayerResponse player(PlayerEnum player) {
    this.player = player;
    return this;
  }

  /**
   * Get player
   * @return player
  */
  @Valid 
  @Schema(name = "player", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    JoinPlayerResponse joinPlayerResponse = (JoinPlayerResponse) o;
    return Objects.equals(this.player, joinPlayerResponse.player);
  }

  @Override
  public int hashCode() {
    return Objects.hash(player);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JoinPlayerResponse {\n");
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

