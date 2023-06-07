package sg.edu.nus.iss.pizzasp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.pizzasp.model.Order;
import sg.edu.nus.iss.pizzasp.repository.PizzaRespository;

@RestController
@RequestMapping("/order")
public class PizzaRESTController {
    @Autowired
    PizzaRespository pRepo;

     @GetMapping(path="{orderId}")
     public ResponseEntity<String> getJsonPage(@PathVariable String orderId){
       Optional<Order> o= pRepo.getOrderbyId(orderId);
       if(o.isEmpty()){
        JsonObject err= Json.createObjectBuilder()
        .add("message","HTTPSTATUS:%s Order: %s do not exist".formatted(HttpStatus.NOT_FOUND.toString(),orderId))
        .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err.toString());
       }




        return ResponseEntity.status(HttpStatus.OK).body(o.get().toJson().toString());

    }
    
}
