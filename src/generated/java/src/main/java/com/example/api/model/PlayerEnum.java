package com.example.api.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets PlayerEnum
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T09:27:53.578119500+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public enum PlayerEnum {
  
  NONE("None"),
  
  VIKING("Viking"),
  
  MONSTER("Monster"),
  
  BOTH("Both");

  private String value;

  PlayerEnum(String value) {
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
  public static PlayerEnum fromValue(String value) {
    for (PlayerEnum b : PlayerEnum.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

