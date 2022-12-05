package uk.ac.ed.inf;

import java.util.ArrayList;
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
    public void droneMove(Order order){
        /**
         * this class should
         */
    }






}
