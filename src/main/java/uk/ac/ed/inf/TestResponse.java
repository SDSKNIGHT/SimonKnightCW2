package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * rump class that exists exclusively to test the function of both Jackson and the REST server.
 */
public class TestResponse {
    @JsonProperty( "greeting" )
    public String greeting ;
}
