package com.rentapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentapp.model.User;
import com.rentapp.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "Login/register";
    }

    @PostMapping("/users/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        String message = userService.registerUser(user);
        model.addAttribute("message", message);
        return "Login/register";
    }

  
}
