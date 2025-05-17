package com.example.generated.api.model;

import java.net.URI;
import java.util.Objects;
import com.example.generated.api.model.Game;
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
 * PagedGameResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-05-17T16:58:09.650237800+02:00[Europe/Prague]", comments = "Generator version: 7.4.0")
public class PagedGameResponse {

  @Valid
  private List<@Valid Game> items = new ArrayList<>();

  private Integer page;

  private Integer size;

  private Integer total;

  public PagedGameResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public PagedGameResponse(List<@Valid Game> items, Integer page, Integer size, Integer total) {
    this.items = items;
    this.page = page;
    this.size = size;
    this.total = total;
  }

  public PagedGameResponse items(List<@Valid Game> items) {
    this.items = items;
    return this;
  }

  public PagedGameResponse addItemsItem(Game itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @NotNull @Valid 
  @Schema(name = "items", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("items")
  public List<@Valid Game> getItems() {
    return items;
  }

  public void setItems(List<@Valid Game> items) {
    this.items = items;
  }

  public PagedGameResponse page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * Get page
   * @return page
  */
  @NotNull 
  @Schema(name = "page", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("page")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public PagedGameResponse size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  */
  @NotNull 
  @Schema(name = "size", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public PagedGameResponse total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  @NotNull 
  @Schema(name = "total", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("total")
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagedGameResponse pagedGameResponse = (PagedGameResponse) o;
    return Objects.equals(this.items, pagedGameResponse.items) &&
        Objects.equals(this.page, pagedGameResponse.page) &&
        Objects.equals(this.size, pagedGameResponse.size) &&
        Objects.equals(this.total, pagedGameResponse.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, page, size, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagedGameResponse {\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

