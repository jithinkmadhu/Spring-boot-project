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
import com.orthofx.owner.vehicle.VehicleDTO;
import com.orthofx.owner.vehicle.VehicleRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<OwnerDTO> getAllOwners() {
		return this.ownerRepository.findAll();
	}
	
	public ResponseEntity<OwnerDTO> getOwnerById(Long ownerId) throws ResourceNotFoundException {
		OwnerDTO owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id : " + ownerId));
		return ResponseEntity.ok().body(owner);
	}
	
	public OwnerDTO createOwner(@RequestBody OwnerDTO owner) {
		return this.ownerRepository.save(owner);
	}
	
	public ResponseEntity<OwnerDTO> updateOwner(Long ownerId, OwnerDTO ownerDetails) throws ResourceNotFoundException{
		OwnerDTO owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		owner.setName(ownerDetails.getName());
		owner.setPhoneNumber(ownerDetails.getPhoneNumber());
		return ResponseEntity.ok(this.ownerRepository.save(owner));
	}
	
	public Map<String, Boolean> deleteOwner(Long ownerId) throws ResourceNotFoundException {
		OwnerDTO owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		this.ownerRepository.delete(owner);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public List<VehicleDTO> getAllVehicles(Long ownerId) {
		List<VehicleDTO> vehicles = new ArrayList<>();
		this.vehicleRepository.findByOwnerId(ownerId).forEach(vehicles::add);
		return vehicles;
	}
	
	public ResponseEntity<VehicleDTO> getVehicleById(Long vehicleId) throws ResourceNotFoundException {
		VehicleDTO vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id : " + vehicleId));
		return ResponseEntity.ok().body(vehicle);
	}
	
	public VehicleDTO createVehicle(VehicleDTO vehicle, Long ownerId) throws ResourceNotFoundException  {
		OwnerDTO owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		VehicleDTO vehicle1 = new VehicleDTO();
		vehicle1.setOwner(owner);
		vehicle1.setModel(vehicle.getModel());
		vehicle1.setRegNo(vehicle.getRegNo());
		return this.vehicleRepository.save(vehicle1);
	}
}
