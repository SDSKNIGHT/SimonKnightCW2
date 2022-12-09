package uk.ac.ed.inf;

import java.awt.*;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

public class Output {
    public static void createDeliveries(ArrayList<Order> orderList,String date) throws IOException {
        JsonArray array = new JsonArray();
        for (Order i:orderList){
            JsonObject object= new JsonObject();
            object.addProperty("orderNo",i.getOrderNo());
            object.addProperty("outcome",i.getOrderOutcome());
            object.addProperty("costInPence",i.getPriceInPence());
            array.add(object);
        }
        String filename ="deliveries-"+date+".json";
        FileWriter fileWriter =new FileWriter(filename);
        fileWriter.flush();

        fileWriter.write(array.toString());

    }


    public static void createFlightpath(ArrayList<FlightPath> flightpath, String date) throws IOException {
        JsonArray array = new JsonArray();
        for (FlightPath i:flightpath )
        {

            JsonObject object = new JsonObject();
            object.addProperty("orderNo",i.getOrderNo());
            object.addProperty("fromLongitude", i.getFromLongitude());
            object.addProperty("fromLatitude", i.getFromLatitude());
            object.addProperty("angle", i.getAngle());
            object.addProperty("toLongitude", i.getToLongitude());
            object.addProperty("toLatitude", i.getToLatitude());
            object.addProperty("ticksSinceStartOfCalculation", i.getTicksSinceStartOfCalculation());
            array.add(object);


        }
        String filename = "flightpath-"+date+".json";
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(array.toString());
    }

}
