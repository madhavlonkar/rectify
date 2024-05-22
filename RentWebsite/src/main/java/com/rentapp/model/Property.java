package com.rentapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String place;
	private int area;
	private int numberOfBedrooms;
	private int numberOfBathrooms;
	private String nearbyHospitals;
	private String nearbyColleges;
	private int likes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	private User seller;
	
	@Transient
    private boolean isLiked;
	
	

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getNumberOfBedrooms() {
		return numberOfBedrooms;
	}

	public void setNumberOfBedrooms(int numberOfBedrooms) {
		this.numberOfBedrooms = numberOfBedrooms;
	}

	public int getNumberOfBathrooms() {
		return numberOfBathrooms;
	}

	public void setNumberOfBathrooms(int numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}

	public String getNearbyHospitals() {
		return nearbyHospitals;
	}

	public void setNearbyHospitals(String nearbyHospitals) {
		this.nearbyHospitals = nearbyHospitals;
	}

	public String getNearbyColleges() {
		return nearbyColleges;
	}

	public void setNearbyColleges(String nearbyColleges) {
		this.nearbyColleges = nearbyColleges;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	

	public Property(Long id, String place, int area, int numberOfBedrooms, int numberOfBathrooms,
			String nearbyHospitals, String nearbyColleges, int likes, User seller) {
		super();
		this.id = id;
		this.place = place;
		this.area = area;
		this.numberOfBedrooms = numberOfBedrooms;
		this.numberOfBathrooms = numberOfBathrooms;
		this.nearbyHospitals = nearbyHospitals;
		this.nearbyColleges = nearbyColleges;
		this.likes = likes;
		this.seller = seller;
	}

	public Property() {
		super();
	}

	// Constructors, getters, and setters
}
