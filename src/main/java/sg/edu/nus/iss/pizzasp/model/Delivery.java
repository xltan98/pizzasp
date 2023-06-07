package sg.edu.nus.iss.pizzasp.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable {
    @Size(min=3, message="your name should have min 3 characters")
    @NotBlank(message="name should not be empty")
    private String name;

    @NotBlank(message="address should not be empty")
    private String address;

    @NotBlank(message="phone number cannot be empty")
    @Pattern(regexp="\\d{8}", message="please provide a valid phone numer")
    private String phone;

    private boolean rush=false;

    private String comments;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Delivery create(JsonObject o){
        Delivery d = new Delivery();
        d.setAddress(o.getString("address"));
        d.setComments(o.getString("comments"));
        d.setPhone(o.getString("phone"));
        d.setName(o.getString("name"));
        d.setRush(o.getBoolean("rush"));
        return d;
    }

    


    
}
