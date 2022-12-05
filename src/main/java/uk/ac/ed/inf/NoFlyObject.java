package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
}


