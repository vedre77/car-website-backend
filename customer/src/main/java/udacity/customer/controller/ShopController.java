package udacity.customer.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import udacity.customer.model.Item;
import udacity.customer.services.OrderService;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private OrderService orderService;

    public ShopController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public String getItemList(Model model) {
        List<Item> allItems = orderService.listCars();
        model.addAttribute("items", allItems);
        return "shop";
    }

    @PostMapping("/order/{itemId}")
    public String addItemToOrder(@PathVariable Integer itemId, Authentication authentication, Model model) {
        String username = authentication.getName();
        orderService.createOrder(username, itemId);
        return "redirect:/shop";
    }
}
