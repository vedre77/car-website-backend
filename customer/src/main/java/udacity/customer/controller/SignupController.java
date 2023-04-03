package udacity.customer.controller;

import udacity.customer.model.User;
import udacity.customer.model.UserForm;
import udacity.customer.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(UserForm userForm, Model model) {
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, UserForm userForm, Model model) {

        model.addAttribute("userForm", userForm);
        String signupError = null;
        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }
        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }
        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
