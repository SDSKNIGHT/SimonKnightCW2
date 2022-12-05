package uk.ac.ed.inf;

public class FlightPath {
    String orderNo;
    double fromLongitude;
    double fromLatitude;
    double angle;
    double toLongitude;
    double toLatitude;
    int ticksSinceStartOfCalculation;

    public FlightPath(String orderNo, double fromLongitude, double fromLatitude, double angle, double toLongitude, double toLatitude, int ticksSinceStartOfCalculation) {
        this.orderNo = orderNo;
        this.fromLongitude = fromLongitude;
        this.fromLatitude = fromLatitude;
        this.angle = angle;
        this.toLongitude = toLongitude;
        this.toLatitude = toLatitude;
        this.ticksSinceStartOfCalculation = ticksSinceStartOfCalculation;

    }
}


