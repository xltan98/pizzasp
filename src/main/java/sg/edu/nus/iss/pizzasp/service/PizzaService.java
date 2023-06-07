package sg.edu.nus.iss.pizzasp.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import sg.edu.nus.iss.pizzasp.model.Delivery;
import sg.edu.nus.iss.pizzasp.model.Order;
import sg.edu.nus.iss.pizzasp.model.Pizza;

@Service
public class PizzaService {

    Map<String,Float> pizzaCost= new HashMap<>();
    Map<String,Float> pizzaSize= new HashMap<>();
    
    public void pizzaCost(){
        pizzaCost.put("bella", 30f);
        pizzaCost.put("margherita",22f);
        pizzaCost.put("spianatacalabrese",30f);
        pizzaCost.put("marinara",30f);
        pizzaCost.put("trio formaggio",25f);
    }

    public void pizzaSize(){
        pizzaSize.put("sm",1f);
        pizzaSize.put("md",1.2f);
        pizzaSize.put("lg",1.5f);
    }

    public Map<String, Float> getPizzaCost() {
        return pizzaCost;
    }

    public Map<String, Float> getPizzaSize() {
        return pizzaSize;
    }

    public void calculateOrder(Order o){
        float totalCost;
        pizzaCost();
        pizzaSize();

       totalCost=pizzaCost.get(o.getPizza().getPizza().toLowerCase())
       *pizzaSize.get(o.getPizza().getSize().toLowerCase())
       *(o.getPizza().getQuantity());
       o.setPizzaTotal(totalCost);

        if(o.getDelivery().isRush()){
            totalCost=(float) (o.getPizzaTotal()+2);
            o.setRushCost(2);
        }
        o.setTotal(totalCost);

        

    }

    public Order createOrder(Pizza p,Delivery d){
        Order o = new Order();
        String orderId=UUID.randomUUID().toString().replace("-","").substring(0,8);
        o.setOrderId(orderId);
        o.setDelivery(d);
        o.setPizza(p);
        calculateOrder(o);
        return o;
    }

    public List<ObjectError> validatePizzaOrder(Pizza p){
        List<ObjectError> errors = new LinkedList<>();
        FieldError error;

        pizzaCost();
        pizzaSize();

        if(!pizzaCost.containsKey(p.getPizza())){
            error= new FieldError("pizza","pizza","We do not have %s pizza".formatted(p.getPizza()));
            errors.add(error);
        }

        if(!pizzaSize.containsKey(p.getSize())){
            error= new FieldError("size","size","We do not have %s pizza size".formatted(p.getSize()));
            
            errors.add(error);

        }
        return errors;
    }

    


    
}
