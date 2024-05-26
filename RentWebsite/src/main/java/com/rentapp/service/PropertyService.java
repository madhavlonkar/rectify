package com.rentapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rentapp.model.Property;
import com.rentapp.model.User;

public interface PropertyService {
    Property addProperty(Property property);
    List<Property> getPropertiesBySeller(User seller);
    Property updateProperty(Property property);
    void deleteProperty(Long id);
    Property getPropertyById(Long id);
    List<Property> getAllProperties();
    List<Property> filterProperties(String place, int minArea, int maxArea, int minBedrooms, int maxBedrooms);
	Page<Property> getAllProperties(Pageable pageable);

}
