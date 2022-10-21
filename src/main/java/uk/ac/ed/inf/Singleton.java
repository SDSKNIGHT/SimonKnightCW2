package uk.ac.ed.inf;

import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Singleton {

    private static Singleton instance = null;
    private static CentralAreaPoint[] centralAreaPoints;
    private static String url = "https://ilp-rest.azurewebsites.net/";

    public Singleton(){
        try{
            URL urlFinal =new URL(url+"centralArea");
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


    /**
     * get instance method ensures only one instance of Singleton exists at any time.
     * @return
     */
    public static Singleton getInstance(){
        if(instance==null){
            Singleton instance = new Singleton();
        }
        return instance;
    }




}
