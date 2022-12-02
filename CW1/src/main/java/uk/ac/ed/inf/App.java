package uk.ac.ed.inf;

import java.net.URL;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws Exception {
        Restaurant steve = new Restaurant();
        LngLat doug = new LngLat(-3.518500,55.943);
        System.out.println( doug.inCentralArea());
        URL dan = new URL ("https://ilp-rest.azurewebsites.net/");


        System.out.println(Restaurant.getRestaurantsFromRestServer(dan)[0].name);
        int j = Order.getDeliveryCost(Restaurant.getRestaurantsFromRestServer( dan),"Margarita");
        System.out.println(j);
        //System.out.println(Restaurant.getRestaurantsFromRestServer( dan));

    }

}