package com.orthofx.owner.vehicle;

import com.orthofx.owner.owner.OwnerDTO;

public class VehicleDTO {

	private Long id;
	private String regNo;
	private String model;
	private OwnerDTO owner;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public OwnerDTO getOwner() {
		return owner;
	}
	public void setOwner(OwnerDTO owner) {
		this.owner = owner;
	}
	
	
}
