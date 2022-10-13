package com.orthofx.owner.owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.orthofx.owner.exception.ResourceNotFoundException;
import com.orthofx.owner.vehicle.Vehicle;
import com.orthofx.owner.vehicle.VehicleRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Owner> getAllOwners() {
		return this.ownerRepository.findAll();
	}
	
	public ResponseEntity<Owner> getOwnerById(Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id : " + ownerId));
		return ResponseEntity.ok().body(owner);
	}
	
	public Owner createOwner(@RequestBody Owner owner) {
		return this.ownerRepository.save(owner);
	}
	
	public ResponseEntity<Owner> updateOwner(Long ownerId, Owner ownerDetails) throws ResourceNotFoundException{
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		owner.setName(ownerDetails.getName());
		owner.setPhoneNumber(ownerDetails.getPhoneNumber());
		return ResponseEntity.ok(this.ownerRepository.save(owner));
	}
	
	public Map<String, Boolean> deleteOwner(Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		this.ownerRepository.delete(owner);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public List<Vehicle> getAllVehicles(Long ownerId) {
		List<Vehicle> vehicles = new ArrayList<>();
		this.vehicleRepository.findByOwnerId(ownerId).forEach(vehicles::add);
		return vehicles;
	}
	
	public ResponseEntity<Vehicle> getVehicleById(Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id : " + vehicleId));
		return ResponseEntity.ok().body(vehicle);
	}
	
	public Vehicle createVehicle(Vehicle vehicle, Long ownerId) throws ResourceNotFoundException  {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setOwner(owner);
		vehicle1.setModel(vehicle.getModel());
		vehicle1.setRegNo(vehicle.getRegNo());
		return this.vehicleRepository.save(vehicle1);
	}
}
