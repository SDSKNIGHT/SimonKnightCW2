package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Restaurant {
    @JsonProperty("name")
    public String name;
    @JsonProperty("latitute")
    public double lat;
    @JsonProperty("longitude")
    public double lng;
    @JsonProperty("menu")
    public Menu[] menu;

    public Menu[] GetMenu(){
        return menu;

    }
    static Restaurant[] getRestaurantsFromRestServer(URL serverBaseAddress){
        try{
            String baseAddress = serverBaseAddress.toString();
            URL restaurantsURL = new URL (baseAddress+ "restaurants");
            Restaurant[] restaurantArray = new ObjectMapper().readValue( restaurantsURL, Restaurant[].class);
        } catch(MalformedURLException exception){
            exception.printStackTrace();
        } catch (IOException exception){
            exception.printStackTrace();
        }
        return null;

    }
}
