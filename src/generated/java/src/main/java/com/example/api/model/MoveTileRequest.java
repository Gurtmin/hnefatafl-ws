package com.example.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MoveTileRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class MoveTileRequest {

  private Integer x;

  private Integer y;

  public MoveTileRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public MoveTileRequest(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public MoveTileRequest x(Integer x) {
    this.x = x;
    return this;
  }

  /**
   * Get x
   * minimum: 1
   * maximum: 11
   * @return x
  */
  @NotNull @Min(1) @Max(11) 
  @Schema(name = "x", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("x")
  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public MoveTileRequest y(Integer y) {
    this.y = y;
    return this;
  }

  /**
   * Get y
   * minimum: 1
   * maximum: 11
   * @return y
  */
  @NotNull @Min(1) @Max(11) 
  @Schema(name = "y", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("y")
  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MoveTileRequest moveTileRequest = (MoveTileRequest) o;
    return Objects.equals(this.x, moveTileRequest.x) &&
        Objects.equals(this.y, moveTileRequest.y);
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MoveTileRequest {\n");
    sb.append("    x: ").append(toIndentedString(x)).append("\n");
    sb.append("    y: ").append(toIndentedString(y)).append("\n");
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

