package sg.edu.nus.iss.pizzasp.model;

import java.io.Serializable;

import org.json.JSONObject;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza implements Serializable{

@NotNull(message="please select a pizza")
    private String pizza;

@NotNull (message="please select pizza size")
    private String size;

@Min(value=1, message="you must order at least 1 pizza")
@Max(value=10, message="you only can order less than 10 pizza")
    private int quantity;

    

    public Pizza() {
    }
    
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public static Pizza create(JsonObject o){
        Pizza p = new Pizza();
        p.setPizza(o.getString("pizza"));
        p.setQuantity(o.getInt("quantity"));
        p.setSize(o.getString("size"));

        return p;
        
    }


    

    

    
}
