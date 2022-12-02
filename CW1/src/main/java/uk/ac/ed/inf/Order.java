package uk.ac.ed.inf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Order {
    @JsonProperty("orderNo")
    public int orderNo;
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

    /**
     *  helper function for the getDeliveryCost that identifies whether the order is feasible from one restaurant;
     * @param restaurants
     * @param order
     * @return
     */
    public static Restaurant returnRestaurant(Restaurant[] restaurants, String... order){
        Restaurant actual =null;
        for (Restaurant r:restaurants){
            int items =0;
            for(int i=0; i<r.getMenu().length;i++){


                for(int j=0; j<order.length;j++){

                    if((r.getMenu()[i].menuItem.equals(order[j]))){  //absolute pain here, forgot that == in java was not equals for strings in jav
                        items=items+1;

                        if(items==order.length){
                            actual = r;
                            return actual;
                        }
                    }

                }
            }
        }
        return actual;
    }
    public static int getDeliveryCost(Restaurant[] restaurants, String... order) throws Exception {
        Restaurant actual = returnRestaurant(restaurants, order);
        if(actual == null){
            throw new Exception("No restaurant delivers the given selection");
        }
        int priceInP = 100;
        for(int i=0; i<actual.getMenu().length;i++) {
            for (int j = 0; j < order.length; j++) {
                if (actual.getMenu()[i].menuItem.equals(order[j])) {
                    priceInP = priceInP + actual.getMenu()[i].menuItemPrice;
                }
            }
        }
        return priceInP;
    }

    /**
     * enums I added because they were in the specification, must be a CW2 thing.
     */
    public enum OrderOutcome {
        Delivered,
        ValidButNotDelivered ,
        InvalidCardNumber ,
        InvalidExpiryDate ,
        InvalidCvv ,
        InvalidTotal ,
        InvalidPizzaNotDefined ,
        InvalidPizzaCount ,
        InvalidPizzaCombinationMultipleSuppliers ,
        Invalid
    }


}
