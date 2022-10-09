package com.orthofx.owner.owner;

public class Owner {

	private int id;
	private String name;
	private String phoneNumber;
	private String vehicle;
	
	public Owner() {
		
	}
	
	public Owner(int id, String name, String phoneNumber, String vehicle) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.vehicle = vehicle;
	}
	
	public int getId() {
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
	
}
