package uk.ac.ed.inf;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.simple.JSONObject;

public class Output {
    public static void createDeliveries(ArrayList<Order> deliveredOrders) {
        ObjectMapper mapper = new ObjectMapper();
        for (Order i:deliveredOrders){
            ObjectNode order
        }
    }


    public static void createFlightpath(ArrayList<FlightPath> flightpath) {
    }
}
