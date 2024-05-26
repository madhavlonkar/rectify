package com.rentapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rentapp.dao.PropertyRepository;
import com.rentapp.model.Property;
import com.rentapp.model.User;
import com.rentapp.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    
    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    @Override
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }
    @Override
    public List<Property> filterProperties(String place, int minArea, int maxArea, int minBedrooms, int maxBedrooms) {
        // Implement filtering logic based on criteria
        return propertyRepository.findByPlaceAndAreaBetweenAndNumberOfBedroomsBetween(place, minArea, maxArea, minBedrooms, maxBedrooms);
    }



    @Override
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getPropertiesBySeller(User seller) {
        return propertyRepository.findBySeller(seller);
    }

    @Override
    public Property updateProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }
}
