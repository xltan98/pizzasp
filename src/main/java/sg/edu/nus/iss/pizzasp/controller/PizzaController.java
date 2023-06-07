package sg.edu.nus.iss.pizzasp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzasp.model.Delivery;
import sg.edu.nus.iss.pizzasp.model.Order;
import sg.edu.nus.iss.pizzasp.model.Pizza;
import sg.edu.nus.iss.pizzasp.repository.PizzaRespository;
import sg.edu.nus.iss.pizzasp.service.PizzaService;

@Controller
public class PizzaController {
    @Autowired
    PizzaService pSvc;
    @Autowired
    PizzaRespository pRepo;

    @GetMapping(path="/")
    public String getLandingPage(Model m,HttpSession s){
        s.invalidate();
        
        //s.setAttribute("pizza", p);

        m.addAttribute("pizza",new Pizza());

        return "index";
        
    }

    @PostMapping(path="/pizza")
    public String getInformation(HttpSession s,@ModelAttribute("pizza") @Valid Pizza p, BindingResult bind, Model m){
        if(bind.hasErrors()){
            return "index";
        }

        List<ObjectError> errors= pSvc.validatePizzaOrder(p);
        if(!errors.isEmpty()){
            for(ObjectError e:errors){
                bind.addError(e);
                return "index";
            }

            
        }
        
        s.setAttribute("pizza", p);
        
        //s.setAttribute("delivery", d);

        m.addAttribute("delivery",new Delivery());
        return "view2";
    }

    @PostMapping(path="/order")
    public String getConfirmation(HttpSession s,@ModelAttribute("delivery") @Valid Delivery d,BindingResult bind, Model m){
       if(bind.hasErrors()){
        return "view2";
       }
      Pizza p= (Pizza) s.getAttribute("pizza");
      //Delivery de= (Delivery)s.getAttribute("delivery");
      
      Order o=pSvc.createOrder(p,d);
       pRepo.saveOrder(o);
      m.addAttribute("order", o);

        return "view3";
    }

    @GetMapping(path="/list")
    public String getAllOrders(Model m){

        List<String> ids =pRepo.getAllOrderId();

        m.addAttribute("ids", ids);

        return "view4";



    }







    
}
