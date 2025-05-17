
package com.example.mongo.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "figure",
    "isSelected",
    "isEnabled",
    "isMoveEnabled"
})
@Generated("jsonschema2pojo")
public class Tile {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("figure")
    private Tile.Figure figure;
    @JsonProperty("isSelected")
    private Boolean isSelected;
    @JsonProperty("isEnabled")
    private Boolean isEnabled;
    @JsonProperty("isMoveEnabled")
    private Boolean isMoveEnabled;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("figure")
    public Tile.Figure getFigure() {
        return figure;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("figure")
    public void setFigure(Tile.Figure figure) {
        this.figure = figure;
    }

    @JsonProperty("isSelected")
    public Boolean getIsSelected() {
        return isSelected;
    }

    @JsonProperty("isSelected")
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @JsonProperty("isEnabled")
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    @JsonProperty("isEnabled")
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @JsonProperty("isMoveEnabled")
    public Boolean getIsMoveEnabled() {
        return isMoveEnabled;
    }

    @JsonProperty("isMoveEnabled")
    public void setIsMoveEnabled(Boolean isMoveEnabled) {
        this.isMoveEnabled = isMoveEnabled;
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
        sb.append(Tile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("figure");
        sb.append('=');
        sb.append(((this.figure == null)?"<null>":this.figure));
        sb.append(',');
        sb.append("isSelected");
        sb.append('=');
        sb.append(((this.isSelected == null)?"<null>":this.isSelected));
        sb.append(',');
        sb.append("isEnabled");
        sb.append('=');
        sb.append(((this.isEnabled == null)?"<null>":this.isEnabled));
        sb.append(',');
        sb.append("isMoveEnabled");
        sb.append('=');
        sb.append(((this.isMoveEnabled == null)?"<null>":this.isMoveEnabled));
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
        result = ((result* 31)+((this.isSelected == null)? 0 :this.isSelected.hashCode()));
        result = ((result* 31)+((this.isMoveEnabled == null)? 0 :this.isMoveEnabled.hashCode()));
        result = ((result* 31)+((this.figure == null)? 0 :this.figure.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.isEnabled == null)? 0 :this.isEnabled.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Tile) == false) {
            return false;
        }
        Tile rhs = ((Tile) other);
        return ((((((this.isSelected == rhs.isSelected)||((this.isSelected!= null)&&this.isSelected.equals(rhs.isSelected)))&&((this.isMoveEnabled == rhs.isMoveEnabled)||((this.isMoveEnabled!= null)&&this.isMoveEnabled.equals(rhs.isMoveEnabled))))&&((this.figure == rhs.figure)||((this.figure!= null)&&this.figure.equals(rhs.figure))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.isEnabled == rhs.isEnabled)||((this.isEnabled!= null)&&this.isEnabled.equals(rhs.isEnabled))));
    }

    @Generated("jsonschema2pojo")
    public enum Figure {

        EMPTY("Empty"),
        DEAD_ODIN("DeadOdin"),
        ESCAPED_ODIN("EscapedOdin"),
        ODIN("Odin"),
        MONSTER("Monster"),
        WARRIOR("Warrior"),
        THRONE("Throne"),
        EXIT("Exit");
        private final String value;
        private final static Map<String, Tile.Figure> CONSTANTS = new HashMap<String, Tile.Figure>();

        static {
            for (Tile.Figure c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Figure(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static Tile.Figure fromValue(String value) {
            Tile.Figure constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
