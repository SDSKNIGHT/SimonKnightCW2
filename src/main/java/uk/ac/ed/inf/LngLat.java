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
    public boolean closeTo (LngLat secPos) {
        if (secPos == null){
            throw new NullPointerException("The input cannot be null");
        }

        if (distanceTo (secPos) < 0.00015) {
            return true;
        } else {
            return false;
        }
    }
    public LngLat nextPosition ( enum angle){



        if (angle == null){
            return this;
            //DO A CONVERSION OF THE ENUM DIRECTIONS INTO VALUES
        } else {
            double rads = //rads calculation
            double newLng = this.lng + Math.cos (rads) * 0.00015;
            double newLat = this.lat + Math.sin (rads) * 0.00015;

            LngLat newPos = new LngLat (newLng, newLat);
            return newPos;
        }
    }



}
