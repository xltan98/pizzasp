package sg.edu.nus.iss.pizzasp.model;

import java.io.Serializable;
import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class Order implements Serializable{

    private String orderId;
    private Delivery delivery;
    private Pizza pizza;
    private double total;
    private double pizzaTotal;
    private double rushCost;

    

    
    public Order() {
    }
    

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public Delivery getDelivery() {
        return delivery;
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }


    public double getPizzaTotal() {
        return pizzaTotal;
    }


    public void setPizzaTotal(double pizzaTotal) {
        this.pizzaTotal = pizzaTotal;
    }


    public double getRushCost() {
        
        return rushCost;
    }


    public void setRushCost(double rushCost) {

        this.rushCost = rushCost;
    }

    // public String getName() { return this.getDelivery().getName();}
    // public String getPizzaName() { return this.getPizza().getPizza();}
    // public String getAddress() { return this.getDelivery().getAddress();}
    // public String getPhone() { return this.getDelivery().getPhone();}
    // public boolean getRush() { return this.getDelivery().isRush();}
    // public String getComments() { return this.getDelivery().getComments();}
    // public String getSize() { return this.getPizza().getSize();}
    // public int getQuantity() { return this.getPizza().getQuantity();}
    

    // public static JsonObject toJSON(String json){
    //     JsonReader r = (JsonReader) Json.createReader(new StringReader(json));
    //     return r.readObject();
    // }
    // // stringreader is a shortcut so u dont need to put toString

    // public Order(Delivery delivery, Pizza pizza) {
    //     this.delivery = delivery;
    //     this.pizza = pizza;
    // }

    // public static Order create(String jsonStr){
    //     JsonObject o = toJSON(jsonStr);
    //     Pizza p = Pizza.create(o);
    //     Delivery d = Delivery.create(o);
    //     Order ord = new Order(d,p);
    //     ord.setOrderId(o.getString("orderId"));
    //     ord.setTotal(o.getJsonNumber("total").doubleValue());
    //     return ord;
    // }

  


    


    // public JsonObject toJSON(){
    //     return Json.createObjectBuilder()
    //             .add("orderId", this.orderId)
    //             .add("name", this.getName())
    //             .add("address", this.getAddress())
    //             .add("phone", this.getPhone())
    //             .add("rush", this.getRush())
    //             .add("comments", this.getComments())
    //             .add("pizza", this.getPizzaName())
    //             .add("size", this.getSize())
    //             .add("quantity", this.getQuantity())
    //             .add("total", this.total)
    //             .build();
    // }

    public JsonObject toJson(){
        return Json.createObjectBuilder()
        .add("orderId", this.orderId)
        .add("name", this.getDelivery().getName())
        .add("address", this.getDelivery().getAddress())
        .add("phone",this.getDelivery().getPhone())
        .add("rush",this.getDelivery().isRush())
        .add("comments", this.getDelivery().getComments())
        .add("pizza",this.getPizza().getPizza())
        .add("size",this.getPizza().getSize())
        .add("quantity",this.getPizza().getQuantity())
        .add("total", this.getTotal())
        
        .build();
        


    }

    public static Order createFromJson(String jsonStr){
        JsonReader r = Json.createReader(new StringReader(jsonStr));
        JsonObject o = r.readObject();
        Pizza p = Pizza.create(o);
        Delivery d= Delivery.create(o);

        Order ord= new Order();
        ord.setDelivery(d);
        ord.setPizza(p);
        ord.setOrderId(o.getString("orderId"));
        ord.setTotal(o.getJsonNumber("total").doubleValue());
        return ord;


    }

    

    
}
