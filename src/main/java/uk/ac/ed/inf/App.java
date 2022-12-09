package uk.ac.ed.inf;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;


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


        /**
         *
         */
        for (int i=0;i<map.getOrderNumber();i++){
            Order currentOrder = map.getClosestOrder();
            currentOrder.getDeliveryCost(client.getRestaurants());
            currentOrder.orderTester();
            if(currentOrder.orderOutcome == Order.OrderOutcome.ValidButNotDelivered ) {
                if (drone.getRemainingBattery() <= 0) {
                    System.out.println("out of battery");
                    break;
                }
                ArrayList<FlightPath> path =drone.findPath(currentOrder);
                if (drone.getRemainingBattery() >= 0) {
                    currentOrder.orderOutcome = Order.OrderOutcome.Delivered;
                    drone.addFlightPath(path);
                }
            }
            drone.addOrder(currentOrder);

        }
        System.out.println("Battery remaining: " +drone.getRemainingBattery());

        ArrayList<Order> deliveredOrders=drone.getOrdersDelivered();
        ArrayList<FlightPath> flightpath = drone.getFlightpaths();
        for (FlightPath i:flightpath){
            LngLat a =new LngLat (i.fromLongitude,i.fromLatitude);
            LngLat b =new LngLat (i.toLongitude,i.toLatitude);
            System.out.println(a.distanceTo(b));
        }

        createGeojson(flightpath,date);
        Output.createDeliveries(deliveredOrders, date);
        Output.createFlightpath(flightpath,date);


    }
    public static void createGeojson(ArrayList<FlightPath> flightpaths, String date){
        List<Point> thisFlightPath = new ArrayList<>();
        thisFlightPath.add(Point.fromLngLat(flightpaths.get(0).fromLongitude, flightpaths.get(0).fromLatitude));
        for(FlightPath i:flightpaths){
            thisFlightPath.add(Point.fromLngLat(i.toLongitude,i.toLatitude));
        }
        LineString flightLine =LineString.fromLngLats(thisFlightPath);
        Feature flightpathFeature = Feature.fromGeometry((Geometry) flightLine);
        ArrayList<Feature> flightpathList = new ArrayList<Feature>();
        flightpathList.add(flightpathFeature);
        FeatureCollection flightpathFC = FeatureCollection.fromFeatures(flightpathList);
        String flightpathJson = flightpathFC.toJson();

        // write the geojson file
        try {
            FileWriter writer = new FileWriter(
                    "drone-" +date+".geojson");
            writer.write(flightpathJson);
            writer.close();
            System.out.println("geojson created");
        } catch (IOException e) {
            System.err.println("failed to generate geojson");
            e.printStackTrace();
        }

    }

}