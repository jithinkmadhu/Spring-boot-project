package com.orthofx.owner.owner;

import java.util.ArrayList;
import java.util.List;

import com.orthofx.owner.vehicle.Vehicle;

public class OwnerDTO {

	private Long id;
	private String name;
	private String phoneNumber;
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
}
