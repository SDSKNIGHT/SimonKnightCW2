package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CentralAreaPoint {
    @JsonProperty("name")
    public String name;
    @JsonProperty("longitude")
    public double lng;
    @JsonProperty("latitude")
    public double lat;

}
