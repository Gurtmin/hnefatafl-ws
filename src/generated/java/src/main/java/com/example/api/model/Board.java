package com.example.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.api.model.BoardRowsInner;
import com.example.api.model.TilePosition;
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
 * Board
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class Board {

  @Valid
  private List<@Valid BoardRowsInner> rows = new ArrayList<>();

  private TilePosition selectedTile;

  public Board() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Board(List<@Valid BoardRowsInner> rows) {
    this.rows = rows;
  }

  public Board rows(List<@Valid BoardRowsInner> rows) {
    this.rows = rows;
    return this;
  }

  public Board addRowsItem(BoardRowsInner rowsItem) {
    if (this.rows == null) {
      this.rows = new ArrayList<>();
    }
    this.rows.add(rowsItem);
    return this;
  }

  /**
   * Get rows
   * @return rows
  */
  @NotNull @Valid @Size(min = 11, max = 11) 
  @Schema(name = "rows", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("rows")
  public List<@Valid BoardRowsInner> getRows() {
    return rows;
  }

  public void setRows(List<@Valid BoardRowsInner> rows) {
    this.rows = rows;
  }

  public Board selectedTile(TilePosition selectedTile) {
    this.selectedTile = selectedTile;
    return this;
  }

  /**
   * Get selectedTile
   * @return selectedTile
  */
  @Valid 
  @Schema(name = "selectedTile", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("selectedTile")
  public TilePosition getSelectedTile() {
    return selectedTile;
  }

  public void setSelectedTile(TilePosition selectedTile) {
    this.selectedTile = selectedTile;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Board board = (Board) o;
    return Objects.equals(this.rows, board.rows) &&
        Objects.equals(this.selectedTile, board.selectedTile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rows, selectedTile);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Board {\n");
    sb.append("    rows: ").append(toIndentedString(rows)).append("\n");
    sb.append("    selectedTile: ").append(toIndentedString(selectedTile)).append("\n");
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

