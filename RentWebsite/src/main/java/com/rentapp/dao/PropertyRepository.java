package com.rentapp.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentapp.model.Property;
import com.rentapp.model.User;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findBySeller(User seller);
    List<Property> findByPlaceAndAreaBetweenAndNumberOfBedroomsBetween(String place, int minArea, int maxArea, int minBedrooms, int maxBedrooms);
}
