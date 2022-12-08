package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Map;

public class Order {
    @JsonProperty("orderNo")
    public String  orderNo;
    @JsonProperty("orderDate")
    public Date date;
    @JsonProperty("customer")
    public String customerName;
    @JsonProperty("creditCardNumber")
    public int cardNumber;
    @JsonProperty("creditCardExpiry")
    public String expiryDate;
    @JsonProperty("cvv")
    public int cvv;
    @JsonProperty("priceTotalInPence")
    public int priceInPence;
    @JsonProperty("orderItems")
    public String[] order;
    public Restaurant restaurantVal=null;
    public Double distance;
    public OrderOutcome orderOutcome;

    /**
     *  helper function for the getDeliveryCost that identifies whether the order is feasible from one restaurant;
     * @param restaurants
     * @param order
     * @return
     */
    public Restaurant returnRestaurant(Restaurant[] restaurants, String... order){
        if (restaurantVal == null) {
            Restaurant actual = null;
            for (Restaurant r : restaurants) {
                int items = 0;
                for (int i = 0; i < r.getMenu().length; i++) {


                    for (int j = 0; j < order.length; j++) {

                        if ((r.getMenu()[i].menuItem.equals(order[j]))) {  //absolute pain here, forgot that == in java was not equals for strings in jav
                            items = items + 1;

                            if (items == order.length) {
                                actual = r;

                                restaurantVal = actual;
                                return actual;
                            }
                        }

                    }
                }
            }
            this.orderOutcome = OrderOutcome.InvalidPizzaNotDefined;
            return actual;
        }
        return restaurantVal;
    }
    public String getOrderNo(){
        return this.orderNo;
    }

    public String getOrderOutcome() {
        return orderOutcome.toString();
    }

    public int getDeliveryCost(Restaurant[] restaurants, String... order) throws Exception {
        if (this.restaurantVal == null) {
            Restaurant actual = returnRestaurant(restaurants, order);
        }

        if(this.restaurantVal==null){
            throw new Exception("No restaurant delivers the given selection");
        }
        int priceInP = 100;
        for(int i=0; i<this.restaurantVal.getMenu().length;i++) {
            for (int j = 0; j < order.length; j++) {
                if (this.restaurantVal.getMenu()[i].menuItem.equals(order[j])) {
                    priceInP = priceInP + this.restaurantVal.getMenu()[i].menuItemPrice;
                }
            }
        }
        return priceInP;
    }
    static Order[] getOrdersForDate(String baseAddress, String date){
        try{

            URL orderURL = new URL (baseAddress+ "order/"+date);
            Order[] orderArray = new ObjectMapper().readValue( orderURL, Order[].class);
            return orderArray;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public double orderDistance(Restaurant[] restaurants){
        if (this.restaurantVal == null){
            if (this.restaurantVal == null) {
                Restaurant actual = returnRestaurant(restaurants, order);
            }
        }
        if (this.restaurantVal!= null) {

            if (this.distance == null) {
                distance = restaurantVal.getDistance();

            }
        }
        return distance;
    }
    /**
     * enums for delivery status.
     */
    public enum OrderOutcome {
        Delivered,//success
        ValidButNotDelivered ,//success, no battery
        InvalidCardNumber , //card format wrong
        InvalidExpiryDate ,//expiry date format wrong
        InvalidCvv ,//cvv formatted wrong
        InvalidTotal ,
        InvalidPizzaNotDefined ,// non real pizza included
        InvalidPizzaCount ,
        InvalidPizzaCombinationMultipleSuppliers ,
        Invalid
    }


}
