package uk.ac.ed.inf;

import java.net.MalformedURLException;
import java.net.URL;

public class App
{
    public static void main( String[] args ) throws Exception {
        String date = args[0];
        String url = args[1];
        String seed = args[2];
        RESTClient client =new RESTClient(url);

        MapModel map = new MapModel(client, date);
        Drone drone =new Drone(map,map.getStartPos());
        //move drone


        /** making the fucking algo, also outputing delivries and also flightpath
         *
         */

        /**
         * this next section would make the Geojson file
         */





    }

}