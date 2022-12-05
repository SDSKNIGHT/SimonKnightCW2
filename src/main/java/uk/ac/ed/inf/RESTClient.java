package uk.ac.ed.inf;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTClient {
    private String urlString;

    public RESTClient(String url) throws MalformedURLException {
        this.urlString = url;
        try {
            if (!urlString.endsWith("/")) {
                urlString = urlString + "/";
            }
            URL testUrl = new URL(urlString+"test/HelloWorld");
            TestResponse response = new ObjectMapper().readValue(testUrl, TestResponse.class ) ;


            if( ! response.greeting.endsWith ("HelloWorld")) {
                throw new RuntimeException ( "Test_Case_Failed" ) ;
            }
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public getCentralArea(){

    }
    public getNoFlyZones(){
        NoFlyObject[] Result = NoFlyObject.getNoFlyObjects(urlString);
        return Result;

    }
    public Restaurant[] getRestaurants(){
        Restaurant[] Result=Restaurant.getRestaurantsFromRestServer(urlString);
        return Result;

    }
    public Order[] getOrders(String date){
        Order[] Result = Order.getOrdersForDate(urlString,date);



        return Result;
    }




}
