package com.example.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.generated.api.model.TilePosition;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SelectedTileInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T16:58:09.650237800+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class SelectedTileInfo {

  private TilePosition selectedTile;

  @Valid
  private List<@Valid TilePosition> allowedMoves;

  public SelectedTileInfo() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public SelectedTileInfo(TilePosition selectedTile) {
    this.selectedTile = selectedTile;
  }

  public SelectedTileInfo selectedTile(TilePosition selectedTile) {
    this.selectedTile = selectedTile;
    return this;
  }

  /**
   * Get selectedTile
   * @return selectedTile
  */
  @NotNull @Valid 
  @Schema(name = "selectedTile", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("selectedTile")
  public TilePosition getSelectedTile() {
    return selectedTile;
  }

  public void setSelectedTile(TilePosition selectedTile) {
    this.selectedTile = selectedTile;
  }

  public SelectedTileInfo allowedMoves(List<@Valid TilePosition> allowedMoves) {
    this.allowedMoves = allowedMoves;
    return this;
  }

  public SelectedTileInfo addAllowedMovesItem(TilePosition allowedMovesItem) {
    if (this.allowedMoves == null) {
      this.allowedMoves = new ArrayList<>();
    }
    this.allowedMoves.add(allowedMovesItem);
    return this;
  }

  /**
   * Get allowedMoves
   * @return allowedMoves
  */
  @Valid 
  @Schema(name = "allowedMoves", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("allowedMoves")
  public List<@Valid TilePosition> getAllowedMoves() {
    return allowedMoves;
  }

  public void setAllowedMoves(List<@Valid TilePosition> allowedMoves) {
    this.allowedMoves = allowedMoves;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SelectedTileInfo selectedTileInfo = (SelectedTileInfo) o;
    return Objects.equals(this.selectedTile, selectedTileInfo.selectedTile) &&
        Objects.equals(this.allowedMoves, selectedTileInfo.allowedMoves);
  }

  @Override
  public int hashCode() {
    return Objects.hash(selectedTile, allowedMoves);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SelectedTileInfo {\n");
    sb.append("    selectedTile: ").append(toIndentedString(selectedTile)).append("\n");
    sb.append("    allowedMoves: ").append(toIndentedString(allowedMoves)).append("\n");
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

