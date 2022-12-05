package uk.ac.ed.inf;

import java.util.List;

public class MapModel {

    public static final double APPLETON_TOWER_LONGITUDE = -3.186874;
    public static final double APPLETON_TOWER_LATITUDE = 55.944494;
    private final LngLat StartPos;
    private final RESTClient REST;

    private List<NoFlyObject> noFlyZones;
    private List<Order> orders;
    private List<Order> sortedOrders;


    public MapModel(RESTClient REST, String date){

    }

    public LngLat getStartPos() {
        return StartPos;
    }
    //this needs to model both the
}
