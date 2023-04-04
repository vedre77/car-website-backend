package udacity.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import udacity.customer.model.Item;
import udacity.customer.model.Order;
import udacity.customer.services.OrderService;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/shop")
    public String getItemList(Model model) {
        List<Item> allItems = orderService.orderCar();
        model.addAttribute("items", allItems);
        return "shop";
    }
}
