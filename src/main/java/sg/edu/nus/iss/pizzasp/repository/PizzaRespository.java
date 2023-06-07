package sg.edu.nus.iss.pizzasp.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzasp.model.Order;

@Repository
public class PizzaRespository {

    @Autowired
    RedisTemplate<String,Object> template;

    public void saveOrder(Order o){
        template.opsForHash().put("Delivery order",o.getOrderId(),o.toJson().toString());
        
    }

    public Optional<Order> getOrderbyId(String id){

        String jsonStr=(String)template.opsForHash().get("Delivery order",id);
        //Order o = new Order();
        //Order.createFromJson(jsonStr);
        if((null==jsonStr)){
            return Optional.empty();
        }

        return  Optional.of(Order.createFromJson(jsonStr));
    }

    public List<String> getAllOrderId(){

        List<String> ids = template.opsForHash().keys("Delivery order").stream()
                .map(Object::toString)
                .collect(Collectors.toList());



        return ids;


    }



    
}
