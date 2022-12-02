package uk.ac.ed.inf;

public record LngLat(double lng, double lat){

    public Boolean inCentralArea(){
        //even odd rule implementation, if any line drawn from a point in any direction intersects an odd number of edges then
        Singleton p=Singleton.getInstance();
        CentralAreaPoint[] centralArea=p.getCentralAreaPoints();
        boolean result = false;
        for (int i=0,j= centralArea.length-1;i< centralArea.length;i++)
        {
            if (lng == centralArea[i].lng&& lat == centralArea[i].lat){
                return true;
                // indicates that this is the Corner of a polygon, which would otherwise flag as false as it would return even
            }
            if((centralArea[i].lat>=lat)!=(centralArea[j].lat>=lat)){
                double slope = (lng-centralArea[i].lng)*(centralArea[j].lat-centralArea[i].lat)
                        -(lat-centralArea[i].lat)*(centralArea[j].lng-centralArea[i].lng);
                if(slope ==0){
                    return true;

                     // indcates whether the point is on a boundary, if so it is in fact intersecting.
                }
                if((slope <0)!=(centralArea[j].lat<centralArea[i].lat)){
                    result = !result;
                    /*
                     if the condition is true it means that there is an intersection and consequently if there were an
                     odd number of intersections it is now even and vice versa
                     */
                }
            }
            j=1;
        }
        return result;


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
    public LngLat nextPosition (Enums.CardinalDirection angle){



        if (angle == null){
            return this;
            //DO A CONVERSION OF THE ENUM DIRECTIONS INTO VALUES
        } else {

            double newLng = this.lng + Math.cos (angle.val) * 0.00015;
            double newLat = this.lat + Math.sin (angle.val) * 0.00015;

            LngLat newPos = new LngLat (newLng, newLat);
            return newPos;
        }
    }




}
