
package com.example.mongo.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rows",
    "selectedTile"
})
@Generated("jsonschema2pojo")
public class Board {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rows")
    private List<Row> rows = new ArrayList<Row>();
    @JsonProperty("selectedTile")
    private TilePosition selectedTile;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rows")
    public List<Row> getRows() {
        return rows;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("rows")
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @JsonProperty("selectedTile")
    public TilePosition getSelectedTile() {
        return selectedTile;
    }

    @JsonProperty("selectedTile")
    public void setSelectedTile(TilePosition selectedTile) {
        this.selectedTile = selectedTile;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Board.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("rows");
        sb.append('=');
        sb.append(((this.rows == null)?"<null>":this.rows));
        sb.append(',');
        sb.append("selectedTile");
        sb.append('=');
        sb.append(((this.selectedTile == null)?"<null>":this.selectedTile));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.rows == null)? 0 :this.rows.hashCode()));
        result = ((result* 31)+((this.selectedTile == null)? 0 :this.selectedTile.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Board) == false) {
            return false;
        }
        Board rhs = ((Board) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.rows == rhs.rows)||((this.rows!= null)&&this.rows.equals(rhs.rows))))&&((this.selectedTile == rhs.selectedTile)||((this.selectedTile!= null)&&this.selectedTile.equals(rhs.selectedTile))));
    }

}
