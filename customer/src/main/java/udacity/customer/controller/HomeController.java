package udacity.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**    returns mock dashboard **/
    @GetMapping("/home")
    public String getDashboard() {
        return "home";
    }

}
