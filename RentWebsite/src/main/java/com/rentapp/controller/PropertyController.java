package com.rentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rentapp.model.Property;
import com.rentapp.model.User;
import com.rentapp.service.PropertyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/add")
    public String showAddPropertyForm(Model model, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        model.addAttribute("property", new Property());
        return "Seller/add-property";
    }

    @PostMapping("/add")
    public String addProperty(@ModelAttribute("property") Property property, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        property.setSeller(seller);
        propertyService.addProperty(property);
        return "redirect:/properties/my";
    }

    @GetMapping("/my")
    public String showMyProperties(Model model, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        model.addAttribute("properties", propertyService.getPropertiesBySeller(seller));
        return "Seller/my-properties";
    }

    @GetMapping("/edit/{id}")
    public String showEditPropertyForm(@PathVariable Long id, Model model, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        Property property = propertyService.getPropertyById(id);
        if (property == null || !property.getSeller().getId().equals(seller.getId())) {
            return "redirect:/properties/my";
        }
        model.addAttribute("property", property);
        return "Seller/edit-property";
    }

    @PostMapping("/update")
    public String updateProperty(@ModelAttribute("property") Property property, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        property.setSeller(seller); // Ensure seller is set when updating
        propertyService.updateProperty(property);
        return "redirect:/properties/my";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable Long id, HttpSession session) {
        User seller = (User) session.getAttribute("user");
        if (seller == null) {
            return "redirect:/login";
        }
        Property property = propertyService.getPropertyById(id);
        if (property != null && property.getSeller().getId().equals(seller.getId())) {
            propertyService.deleteProperty(id);
        }
        return "redirect:/properties/my";
    }
}