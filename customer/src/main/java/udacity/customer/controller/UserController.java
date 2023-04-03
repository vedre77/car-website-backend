package udacity.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    /**
     * Creates a list to store any user.
     * @return list of users
     */
    @GetMapping("/customers")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("customers", users);
        return "customer-list";
    }

}
