package udacity.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import udacity.customer.model.User;
import udacity.customer.repository.UserRepository;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/customers")
    public String getAllUsers(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "customer-profile";
    }

}
