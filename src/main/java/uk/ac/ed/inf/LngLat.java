package uk.ac.ed.inf;

public record LngLat(double lng, double lat){
    public Boolean inCentralArea(){
        return null;
    }
    public double distanceTo (LngLat secPos) {

        if (secPos == null){
            throw new NullPointerException("");
        }

        double x1 = this.lng;
        double x2 = secPos.lng;
        double y1 = this.lat;
        double y2 = secPos.lat;

        // calculate Pythagorean distance of two points
        double distance = Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
        return distance;
    }



}
