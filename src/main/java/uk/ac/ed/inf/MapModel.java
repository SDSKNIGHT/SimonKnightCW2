package uk.ac.ed.inf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapModel {

    public static final double APPLETON_TOWER_LONGITUDE = -3.186874;
    public static final double APPLETON_TOWER_LATITUDE = 55.944494;
    private final LngLat StartPos;
    private final RESTClient REST;

    private NoFlyObject[] noFlyZones;
    private Order[] orders;
    private Restaurant[] restaurants;
    private ArrayList<Order> orders2;
    private CentralArea centralArea;


    public MapModel(RESTClient REST, String date){
        this.REST=REST;
        this.noFlyZones = REST.getNoFlyZones();
        this.orders = REST.getOrders(date);
        this.restaurants =REST.getRestaurants();
        this.StartPos = new LngLat(APPLETON_TOWER_LONGITUDE,APPLETON_TOWER_LATITUDE);
        this.orders2 = new ArrayList<>(Arrays.asList(REST.getOrders(date)));
        this.centralArea = REST.getCentralArea();
    }
    public  Order getClosestOrder (){
        if (orders2.size() ==0){
            return null;
        }
        Order closestOrder = this.orders2.get(0);
        double minDistance=2001;
        for(Order i: this.orders2){
            if(i.orderDistance(restaurants)<minDistance){
                minDistance = i.orderDistance(restaurants);
                closestOrder = i;
            }
        }
        this.orders2.remove(closestOrder);
        return closestOrder;


    }

    public int getOrderNumber(){
        return this.orders.length;
    }
    public LngLat getStartPos() {
        return StartPos;
    }

}
