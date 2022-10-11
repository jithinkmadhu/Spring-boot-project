package com.orthofx.owner.owner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.orthofx.owner.vehicle.Vehicle;

@Entity
@Table(name = "owners")
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "vehicle")
	private String vehicle;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "owner")
	private List<Vehicle> vehicles = new ArrayList<>();
	
	public Owner() {
		super();
	}
	
	public Owner(Long id,String name, String phoneNumber, String vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.vehicle = vehicle;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	public List<Vehicle> getVehicleDetails() {
		return vehicles;
	}
	public void setVehicleDetails(List<Vehicle> employee) {
		this.vehicles = vehicles;
	}
	
}
