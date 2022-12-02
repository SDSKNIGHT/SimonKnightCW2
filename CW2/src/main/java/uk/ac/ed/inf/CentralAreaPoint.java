package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
//object that holds info about coordinate points of central area
public class CentralAreaPoint {
    @JsonProperty("name")
    public String name;
    @JsonProperty("longitude")
    public double lng;
    @JsonProperty("latitude")
    public double lat;

}
