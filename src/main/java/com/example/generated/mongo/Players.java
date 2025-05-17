
package com.example.generated.mongo;

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
    "monster",
    "viking",
    "active"
})
@Generated("jsonschema2pojo")
public class Players {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("monster")
    private Player monster;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("viking")
    private Player viking;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("active")
    private Players.Active active = Players.Active.fromValue("Viking");
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("monster")
    public Player getMonster() {
        return monster;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("monster")
    public void setMonster(Player monster) {
        this.monster = monster;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("viking")
    public Player getViking() {
        return viking;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("viking")
    public void setViking(Player viking) {
        this.viking = viking;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("active")
    public Players.Active getActive() {
        return active;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("active")
    public void setActive(Players.Active active) {
        this.active = active;
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
        sb.append(Players.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("monster");
        sb.append('=');
        sb.append(((this.monster == null)?"<null>":this.monster));
        sb.append(',');
        sb.append("viking");
        sb.append('=');
        sb.append(((this.viking == null)?"<null>":this.viking));
        sb.append(',');
        sb.append("active");
        sb.append('=');
        sb.append(((this.active == null)?"<null>":this.active));
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
        result = ((result* 31)+((this.active == null)? 0 :this.active.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.monster == null)? 0 :this.monster.hashCode()));
        result = ((result* 31)+((this.viking == null)? 0 :this.viking.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Players) == false) {
            return false;
        }
        Players rhs = ((Players) other);
        return (((((this.active == rhs.active)||((this.active!= null)&&this.active.equals(rhs.active)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.monster == rhs.monster)||((this.monster!= null)&&this.monster.equals(rhs.monster))))&&((this.viking == rhs.viking)||((this.viking!= null)&&this.viking.equals(rhs.viking))));
    }

    @Generated("jsonschema2pojo")
    public enum Active {

        MONSTER("Monster"),
        VIKING("Viking");
        private final String value;
        private final static Map<String, Players.Active> CONSTANTS = new HashMap<String, Players.Active>();

        static {
            for (Players.Active c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Active(String value) {
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
        public static Players.Active fromValue(String value) {
            Players.Active constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
