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

    public Menu[] getMenu(){
        return menu;

    }



    static Restaurant[] getRestaurantsFromRestServer(URL serverBaseAddress){
        try{
            String baseAddress = serverBaseAddress.toString();
            if ( !baseAddress.endsWith ( "/" ) ) {
                baseAddress = baseAddress+ "/" ;
            }
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