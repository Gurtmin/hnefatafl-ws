package com.example.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.generated.api.model.Tile;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * BoardRowsInner
 */

@JsonTypeName("Board_rows_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T16:58:09.650237800+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class BoardRowsInner {

  @Valid
  private List<@Valid Tile> cols = new ArrayList<>();

  public BoardRowsInner() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public BoardRowsInner(List<@Valid Tile> cols) {
    this.cols = cols;
  }

  public BoardRowsInner cols(List<@Valid Tile> cols) {
    this.cols = cols;
    return this;
  }

  public BoardRowsInner addColsItem(Tile colsItem) {
    if (this.cols == null) {
      this.cols = new ArrayList<>();
    }
    this.cols.add(colsItem);
    return this;
  }

  /**
   * Get cols
   * @return cols
  */
  @NotNull @Valid @Size(min = 11, max = 11) 
  @Schema(name = "cols", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("cols")
  public List<@Valid Tile> getCols() {
    return cols;
  }

  public void setCols(List<@Valid Tile> cols) {
    this.cols = cols;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoardRowsInner boardRowsInner = (BoardRowsInner) o;
    return Objects.equals(this.cols, boardRowsInner.cols);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cols);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoardRowsInner {\n");
    sb.append("    cols: ").append(toIndentedString(cols)).append("\n");
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

