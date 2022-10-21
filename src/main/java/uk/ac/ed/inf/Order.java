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
