package uk.ac.ed.inf;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Menu {

    //menu Items.
    @JsonProperty("name")
    public String menuItem;

    @JsonProperty("priceInPence")
    public int menuItemPrice;
}
