package udacity.customer.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import udacity.customer.model.Item;
import udacity.customer.model.Order;
import udacity.customer.services.OrderService;
import udacity.customer.services.SaleService;

import java.util.List;

@Controller
public class CheckoutController {

    private OrderService orderService;

    private SaleService saleService;

    public CheckoutController(OrderService orderService, SaleService saleService) {
        this.orderService = orderService;
        this.saleService = saleService;
    }

    @GetMapping("/checkout")
    public String getCheckoutPage(Authentication authentication, Model model) {
        String username = authentication.getName();
        Order userOrder = orderService.findUserOrder(username);
        List<Item> userList = userOrder.getItems();
        model.addAttribute("total", orderService.calculateTotal(username));
        model.addAttribute("orderId", userOrder.getOrderId());
        model.addAttribute("checkoutList", userList);
        return "checkout";
    }

    @PostMapping("checkout/pay")
    public String processPayment(Authentication authentication, Model model) {
        String username = authentication.getName();
        Order userOrder = orderService.findUserOrder(username);
        model.addAttribute("result", "success");
        // final step would be calling a sale service to save the order as a sale
        model.addAttribute("message", "There was an error processing your payment.");
        // set order
        saleService.markOrderAsSale(userOrder);
        return "result";
    }
}
