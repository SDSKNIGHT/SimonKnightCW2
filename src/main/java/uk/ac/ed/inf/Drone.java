package uk.ac.ed.inf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drone {
    private int Battery;
    private final LngLat startPos;
    private final MapModel map;
    private LngLat dronePos;
    private List<Order> deliveredOrders;
    private List<FlightPath> flightPaths;
    public boolean home;
    public Drone(MapModel map, LngLat startPos){
        this.map = map;
        this.startPos=startPos;
        this.dronePos = new LngLat (startPos.lng(), startPos.lat());
        this.Battery = 2000;
        this.deliveredOrders = new ArrayList<Order>();
        this.flightPaths = new ArrayList<FlightPath>();
        this.home = false;
    }
    public ArrayList<FlightPath> findPath(Order order){
        ArrayList<FlightPath> route =new ArrayList<>();
        int ticks = 0;


        while(Battery>=0) {


            Enums.CardinalDirection nextDirection = findNextBestMove(dronePos,order.restaurantVal.lng, order.restaurantVal.lat);
            if (nextDirection == null) {
                break;
            }
            LngLat nextPos = nextPosition(nextDirection);
            ticks +=1;
            FlightPath m = new FlightPath(order.getOrderNo(), dronePos.lng(),
                    dronePos.lat(), nextDirection.val, nextPos.lng(), nextPos.lat(),ticks );
            dronePos = nextPos;
            route.add(m);
            Battery -=1;
        }
        ArrayList<FlightPath> routeClone = new ArrayList<>(route);
        ticks+=1;
        route.add(new FlightPath(order.getOrderNo(), dronePos.lng(),
                dronePos.lat(), null, dronePos.lng(), dronePos.lat(),ticks ));
        Collections.reverse(routeClone);
        for (FlightPath i: routeClone){
            route
        }



        return route;

    }

    private Enums.CardinalDirection findNextBestMove(LngLat startPos, double lng, double lat) {


    }
    private LngLat nextPosition(Enums.CardinalDirection d){
        return dronePos.nextPosition(d);
    }

    private ArrayList<LngLat> planRoute(Order order) {
    }


    public int getRemainingBattery() {
        return this.Battery;
    }

    public ArrayList<Order> getOrderDelivered() {
    }

    public ArrayList<FlightPath> getFlightpaths() {
    }
}

