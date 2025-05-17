package com.example.api.model;

import java.net.URI;
import java.util.Objects;
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
 * Tile
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class Tile {

  /**
   * Gets or Sets figure
   */
  public enum FigureEnum {
    EMPTY("Empty"),
    
    ODIN("Odin"),
    
    DEADODIN("DeadOdin"),
    
    ESCAPEDODIN("EscapedOdin"),
    
    MONSTER("Monster"),
    
    WARRIOR("Warrior"),
    
    THRONE("Throne"),
    
    EXIT("Exit");

    private String value;

    FigureEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static FigureEnum fromValue(String value) {
      for (FigureEnum b : FigureEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private FigureEnum figure;

  private Boolean isSelected;

  private Boolean isEnabled;

  private Boolean isMoveEnabled;

  public Tile() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Tile(FigureEnum figure) {
    this.figure = figure;
  }

  public Tile figure(FigureEnum figure) {
    this.figure = figure;
    return this;
  }

  /**
   * Get figure
   * @return figure
  */
  @NotNull 
  @Schema(name = "figure", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("figure")
  public FigureEnum getFigure() {
    return figure;
  }

  public void setFigure(FigureEnum figure) {
    this.figure = figure;
  }

  public Tile isSelected(Boolean isSelected) {
    this.isSelected = isSelected;
    return this;
  }

  /**
   * Get isSelected
   * @return isSelected
  */
  
  @Schema(name = "isSelected", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isSelected")
  public Boolean getIsSelected() {
    return isSelected;
  }

  public void setIsSelected(Boolean isSelected) {
    this.isSelected = isSelected;
  }

  public Tile isEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
    return this;
  }

  /**
   * Get isEnabled
   * @return isEnabled
  */
  
  @Schema(name = "isEnabled", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isEnabled")
  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public Tile isMoveEnabled(Boolean isMoveEnabled) {
    this.isMoveEnabled = isMoveEnabled;
    return this;
  }

  /**
   * Get isMoveEnabled
   * @return isMoveEnabled
  */
  
  @Schema(name = "isMoveEnabled", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isMoveEnabled")
  public Boolean getIsMoveEnabled() {
    return isMoveEnabled;
  }

  public void setIsMoveEnabled(Boolean isMoveEnabled) {
    this.isMoveEnabled = isMoveEnabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tile tile = (Tile) o;
    return Objects.equals(this.figure, tile.figure) &&
        Objects.equals(this.isSelected, tile.isSelected) &&
        Objects.equals(this.isEnabled, tile.isEnabled) &&
        Objects.equals(this.isMoveEnabled, tile.isMoveEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(figure, isSelected, isEnabled, isMoveEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Tile {\n");
    sb.append("    figure: ").append(toIndentedString(figure)).append("\n");
    sb.append("    isSelected: ").append(toIndentedString(isSelected)).append("\n");
    sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
    sb.append("    isMoveEnabled: ").append(toIndentedString(isMoveEnabled)).append("\n");
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

