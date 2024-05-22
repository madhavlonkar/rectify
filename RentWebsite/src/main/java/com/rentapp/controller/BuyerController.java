package com.rentapp.controller;

import com.rentapp.model.Property;
import com.rentapp.model.User;
import com.rentapp.service.PropertyService;
import com.rentapp.utility.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/buyer")
public class BuyerController {

    @Autowired
    private PropertyService propertyService;

    private EmailService emailService=new EmailService();

    @GetMapping("/properties")
    public String viewAllProperties(Model model) {
        List<Property> properties = propertyService.getAllProperties();
        model.addAttribute("properties", properties);
        return "Buyer/all-properties";
    }

    @PostMapping("/filter")
    public String filterProperties(@RequestParam("place") String place,
                                   @RequestParam("minArea") int minArea,
                                   @RequestParam("maxArea") int maxArea,
                                   @RequestParam("minBedrooms") int minBedrooms,
                                   @RequestParam("maxBedrooms") int maxBedrooms,
                                   Model model) {
        List<Property> properties = propertyService.filterProperties(place, minArea, maxArea, minBedrooms, maxBedrooms);
        model.addAttribute("properties", properties);
        return "Buyer/all-properties";
    }

    @GetMapping("/property/{id}")
    public String viewPropertyDetails(@PathVariable Long id, Model model) {
        Property property = propertyService.getPropertyById(id);
        if (property == null) {
            return "redirect:/buyer/properties";
        }
        model.addAttribute("property", property);
        model.addAttribute("showSellerDetails", false); // Do not show seller details by default
        return "Buyer/property-details";
    }

    @PostMapping("/property/{id}/interested")
    public String interestedInProperty(@PathVariable Long id, Model model, HttpSession session) {
        Property property = propertyService.getPropertyById(id);
        User buyer = (User) session.getAttribute("user");

        if (property == null || buyer == null) {
            return "redirect:/buyer/properties";
        }

        // Send email to buyer with seller's contact details
        String buyerEmail = buyer.getEmail();
        String buyerSubject = "Interested in Property: " + property.getPlace();
        String buyerMessage = String.format("You showed interest in the property located at %s.\n\nSeller Contact Details:\nName: %s %s\nEmail: %s\nPhone: %s",
                property.getPlace(), property.getSeller().getFirstName(), property.getSeller().getLastName(), property.getSeller().getEmail(), property.getSeller().getPhoneNumber());

        emailService.sendEmail(buyerEmail, buyerSubject, buyerMessage);

        // Send email to seller with buyer's contact details
        String sellerEmail = property.getSeller().getEmail();
        String sellerSubject = "New Interest in Your Property: " + property.getPlace();
        String sellerMessage = String.format("A buyer showed interest in your property located at %s.\n\nBuyer Contact Details:\nName: %s %s\nEmail: %s\nPhone: %s",
                property.getPlace(), buyer.getFirstName(), buyer.getLastName(), buyer.getEmail(), buyer.getPhoneNumber());

        emailService.sendEmail(sellerEmail, sellerSubject, sellerMessage);

        model.addAttribute("property", property);
        model.addAttribute("showSellerDetails", true); // Show seller details
        return "Buyer/property-details";
    }
}
