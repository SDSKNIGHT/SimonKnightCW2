package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class NoFlyObject {
    @JsonProperty ("name")
    public String name;
    @JsonProperty ("coordinates")
    public double[][] coordinates;

    static NoFlyObject[] getNoFlyObjects(String baseAddress) {
        try {

            URL url = new URL(baseAddress + "noflyzones");
            NoFlyObject[] orderArray = new ObjectMapper().readValue(url, NoFlyObject[].class);
            return orderArray;
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
    public ArrayList<LngLat> getCoordinates(){
        ArrayList<LngLat> noFlyCoordinates = new ArrayList<>();
        for(double[] i:coordinates){
            noFlyCoordinates.add(new LngLat(i[0],i[1]));

        }
        return noFlyCoordinates;
    }
}


