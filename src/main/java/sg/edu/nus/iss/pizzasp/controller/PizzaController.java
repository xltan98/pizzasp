package sg.edu.nus.iss.pizzasp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sg.edu.nus.iss.pizzasp.model.Pizza;

@Controller
public class PizzaController {

    @GetMapping(path="/")
    public String getLandingPage(Model m){

        m.addAttribute("pizza",new Pizza());

        return "index";
        
    }





    
}
