package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Restaurant {
    @JsonProperty("name")
    public String name;
    @JsonProperty("longitude")
    public double lng;
    @JsonProperty("latitude")
    public double lat;
    @JsonProperty("menu")
    public Menu[] menu;
    public double distance = -1;

    public Menu[] getMenu(){
        return menu;

    }
    public double getDistance(){
        if (distance == -1){
            this.distance = Math.hypot(MapModel.APPLETON_TOWER_LONGITUDE-lng, MapModel.APPLETON_TOWER_LATITUDE-lat);
        }
        return distance;
    }

    /**
     * This is a fuction that takes the address and uses it to get a list of all of the Restaurants on the rest server
     * @param baseAddress
     * @return
     */


    static Restaurant[] getRestaurantsFromRestServer(String baseAddress){
        try{

            URL restaurantsURL = new URL (baseAddress+ "restaurants");
            Restaurant[] restaurantArray = new ObjectMapper().readValue( restaurantsURL, Restaurant[].class);

            return restaurantArray;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}