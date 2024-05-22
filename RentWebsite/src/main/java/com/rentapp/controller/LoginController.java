package com.rentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rentapp.model.User;
import com.rentapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "/Login/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, HttpSession session) {
        User loggedInUser = userService.authenticateUser(user.getEmail(), user.getPassword());
        if (loggedInUser != null) {
            session.setAttribute("user", loggedInUser);
            if (loggedInUser.getUserType().equals("Seller")) {
                return "redirect:/properties/my"; // Redirect sellers to their properties
            } else {
                return "redirect:/buyer/properties"; // Redirect buyers to all properties
            }
        } else {
            return "redirect:/login?error"; // Redirect to login page with error message
        }
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
