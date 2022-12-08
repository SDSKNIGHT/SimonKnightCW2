package uk.ac.ed.inf;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class CentralArea {

    private static CentralArea instance = null;
    private static CentralAreaPoint[] centralAreaPoints;


    public CentralArea(String url){
        try{
            URL urlFinal =new URL( url+"centralArea");
            centralAreaPoints = new ObjectMapper().readValue(urlFinal, CentralAreaPoint[].class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //getters
    public static CentralAreaPoint[] getCentralAreaPoints(){
        return centralAreaPoints;
    }


    public static CentralArea getInstance() {

        return instance;
    }
}
