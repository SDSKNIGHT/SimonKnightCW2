package uk.ac.ed.inf;

import java.awt.geom.Line2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Double.NaN;

public class Drone {
    private int Battery;
    private final LngLat startPos;
    private final MapModel map;
    private LngLat dronePos;
    private ArrayList<Order> Orders;
    private ArrayList<FlightPath> flightPaths;
    public int ticks;

    public Drone(MapModel map, LngLat startPos){
        this.map = map;
        this.startPos=startPos;
        this.dronePos = new LngLat (startPos.lng(), startPos.lat());
        this.Battery = 2000;
        this.Orders = new ArrayList<Order>();
        this.flightPaths = new ArrayList<FlightPath>();
        this.ticks=0;
    }
    public ArrayList<FlightPath> findPath(Order order){
        ArrayList<FlightPath> route =new ArrayList<>();

        dronePos = new LngLat(MapModel.APPLETON_TOWER_LONGITUDE,MapModel.APPLETON_TOWER_LATITUDE);

        while(true) {


            Enums.CardinalDirection nextDirection = findNextBestMove(dronePos,order.restaurantVal.lng, order.restaurantVal.lat);
            if (nextDirection == null) {
                break;
            }
            LngLat nextPos = dronePos.nextPosition(nextDirection);
            ticks +=1;
            FlightPath m = new FlightPath(order.getOrderNo(), dronePos.lng(),
                    dronePos.lat(), nextDirection.val, nextPos.lng(), nextPos.lat(),ticks );
            dronePos = nextPos;
            route.add(m);
            Battery -=1;
        }
        ArrayList<FlightPath> routeClone = new ArrayList<>(route);
        Collections.reverse(routeClone);
        ticks+=1;
        Battery -=1;
        route.add(new FlightPath(order.getOrderNo(), dronePos.lng(),
                dronePos.lat(), NaN, dronePos.lng(), dronePos.lat(),ticks ));

        for (FlightPath i: routeClone){
            ticks+=1;
            Battery -=1;
            double angle1 = i.angle-180;
            if(angle1<0){
                angle1 = 360-angle1;
            }
            double lng =i.toLongitude;
            double lat =i.toLatitude;
            i.toLongitude =i.fromLongitude;
            i.toLatitude =i.fromLatitude;
            i.fromLongitude =lng;
            i.fromLatitude =lat;
            i.ticksSinceStartOfCalculation = ticks;

        }
        route.addAll(routeClone);
        dronePos=new LngLat(route.get(route.size()-1).toLongitude,route.get(route.size()-1).toLatitude);
        ticks+=1;
        Battery -=1;
        route.add(new FlightPath(order.getOrderNo(), dronePos.lng(),
                dronePos.lat(), NaN, dronePos.lng(), dronePos.lat(),ticks ));
        return route;

    }

    private Enums.CardinalDirection findNextBestMove(LngLat startPos, double lng, double lat) {
        LngLat restaurant= new LngLat(lng,lat);
        Enums.CardinalDirection bestMove = null;
        double lowestDistanceEstimate= Double.MAX_VALUE;
        if(dronePos.closeTo(restaurant)){
            return null;
        }
        for(Enums.CardinalDirection i :Enums.CardinalDirection.values()){
            LngLat nextPos = dronePos.nextPosition(i);{
                if(!isValidMove(dronePos,nextPos)){
                    continue;
                }
                double distanceToGoal = nextPos.distanceTo(restaurant);
                if (distanceToGoal<lowestDistanceEstimate){
                    lowestDistanceEstimate=distanceToGoal;
                    bestMove = i;
                }
            }
        }
        return bestMove;


    }

    private boolean isValidMove(LngLat dronePos, LngLat nextPos) {
        Line2D line1 = new Line2D.Double(dronePos.lng(),dronePos.lat(),nextPos.lng(),nextPos.lat());
        for (NoFlyObject i:map.getNoFlyZones()){
            ArrayList<LngLat> noFlyObject = i.getCoordinates();
            int firstInt = noFlyObject.size()-1;
            int counter =0;
            for (LngLat j:noFlyObject){
                LngLat first = noFlyObject.get(firstInt);
                Line2D line2 = new Line2D.Double(first.lng(),first.lat(),j.lng(),j.lat());
                if(line1.intersectsLine(line2)){
                    return false;
                }
                firstInt= counter;
                counter+=1;


            }
        }
        return true;
    }

    private LngLat nextPosition(Enums.CardinalDirection d){
        return dronePos.nextPosition(d);
    }


    public int getRemainingBattery() {
        return this.Battery;
    }

    public void addOrder(Order order){

        Orders.add(order);
    }
    public ArrayList<Order> getOrdersDelivered() {
        return Orders;

    }
    public void addFlightPath(ArrayList<FlightPath> flightPath){
        flightPaths.addAll(flightPath);
    }
    public ArrayList<FlightPath> getFlightpaths() {
        return flightPaths;
    }
}

