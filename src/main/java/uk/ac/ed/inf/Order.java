package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    @JsonProperty("orderNo")
    public String  orderNo;
    @JsonProperty("orderDate")
    public String date;
    @JsonProperty("customer")
    public String customerName;
    @JsonProperty("creditCardNumber")
    public String cardNumber;
    @JsonProperty("creditCardExpiry")
    public String expiryDate;
    @JsonProperty("cvv")
    public String cvv;
    @JsonProperty("priceTotalInPence")
    public int priceInPence;
    @JsonProperty("orderItems")
    public String[] orderItems;
    public Restaurant restaurantVal=null;
    public Double distance;
    public OrderOutcome orderOutcome;

    /**
     *  helper function for the getDeliveryCost that identifies whether the order is feasible from one restaurant;
     * @param restaurants
     * @param order
     * @return
     */
    public Restaurant returnRestaurant(Restaurant[] restaurants, String[] order){
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
    public void orderTester() throws ParseException {
        SimpleDateFormat cardDateFormat = new SimpleDateFormat("MM/yy");
        SimpleDateFormat orderDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date cardDate = cardDateFormat.parse(this.expiryDate);
        Date orderDate = orderDateFormat.parse(this.date);
        if (this.orderOutcome==null) {
            if (this.orderItems.length == 0) {
                this.orderOutcome = OrderOutcome.InvalidPizzaCount;
            } else if (this.getCreditCardNumber().length() != 16) {
                this.orderOutcome = OrderOutcome.InvalidCardNumber;
            } else if (this.cvv.length() != 3) {
                this.orderOutcome = OrderOutcome.InvalidCvv;
            } else if (cardDate.before(orderDate)) {
                this.orderOutcome = OrderOutcome.InvalidExpiryDate;
            }
             else this.orderOutcome = OrderOutcome.ValidButNotDelivered;
        }
    }

    private String getCreditCardNumber() {
        return cardNumber;
    }


    public String getOrderNo(){
        return this.orderNo;
    }

    public String getOrderOutcome() {
        return orderOutcome.toString();
    }

    public int getDeliveryCost(Restaurant[] restaurants ) throws Exception {
        if (this.restaurantVal == null) {
            Restaurant actual = returnRestaurant(restaurants, orderItems);
        }

        if(this.restaurantVal==null){
            this.orderOutcome = OrderOutcome.Invalid;
        }
        int priceInP = 100;
        if(this.restaurantVal!= null) {
            for (int i = 0; i < this.restaurantVal.getMenu().length; i++) {
                for (int j = 0; j < orderItems.length; j++) {
                    if (this.restaurantVal.getMenu()[i].menuItem.equals(orderItems[j])) {
                        priceInP = priceInP + this.restaurantVal.getMenu()[i].menuItemPrice;
                    }
                }
            }
        }
        if (priceInP != priceInPence) {
            this.orderOutcome = OrderOutcome.InvalidTotal;
        }
        return priceInP;
    }
    static Order[] getOrdersForDate(String baseAddress, String date){
        try{

            URL orderURL = new URL (baseAddress+ "orders/"+date);
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
            Restaurant actual = returnRestaurant(restaurants,orderItems);

        }
        if (this.restaurantVal!= null) {

            if (this.distance == null) {
                distance = restaurantVal.getDistance();

            }
        }
        if(this.distance==null){
            this.distance = Double.MAX_VALUE;
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

        Invalid
    }
    public void setOrderOutcome(OrderOutcome outcome){
        this.orderOutcome=outcome;
    }
    public int getPriceInPence(){
        return priceInPence;
    }


}
