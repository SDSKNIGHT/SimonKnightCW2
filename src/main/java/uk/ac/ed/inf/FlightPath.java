package uk.ac.ed.inf;

public class FlightPath {
    String orderNo;
    double fromLongitude;
    double fromLatitude;
    Double angle;
    double toLongitude;
    double toLatitude;
    int ticksSinceStartOfCalculation;

    public FlightPath(String orderNo, double fromLongitude, double fromLatitude, Double angle, double toLongitude, double toLatitude, int ticksSinceStartOfCalculation) {
        this.orderNo = orderNo;
        this.fromLongitude = fromLongitude;
        this.fromLatitude = fromLatitude;
        this.angle = angle;
        this.toLongitude = toLongitude;
        this.toLatitude = toLatitude;
        this.ticksSinceStartOfCalculation = ticksSinceStartOfCalculation;

    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getFromLongitude() {
        return fromLongitude;
    }

    public double getFromLatitude() {
        return fromLatitude;
    }

    public double getAngle() {
        return angle;
    }

    public double getToLongitude() {
        return toLongitude;
    }

    public double getToLatitude() {
        return toLatitude;
    }

    public int getTicksSinceStartOfCalculation() {
        return ticksSinceStartOfCalculation;
    }
}


