package uk.ac.ed.inf;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Singleton {

    private static Singleton instance= null;

    public static Singleton getInstance(){
        if(instance==null){
            Singleton instance = new Singleton();

        }
        return instance;
    }




}
