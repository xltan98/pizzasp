package sg.edu.nus.iss.pizzasp.model;

import java.io.Serializable;

public class Pizza implements Serializable{

    private String pizza;
    private String size;
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
    

    

    
}
