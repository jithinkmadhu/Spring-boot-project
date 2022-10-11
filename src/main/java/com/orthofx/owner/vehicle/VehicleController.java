package com.orthofx.owner.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orthofx.owner.exception.ResourceNotFoundException;
import com.orthofx.owner.owner.Owner;
import com.orthofx.owner.owner.OwnerRepository;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return this.vehicleRepository.findAll();
	}
	
	@GetMapping("owners/{ownerId}/vehicles")
	public List<Vehicle> getAllVehicles(@PathVariable Long ownerId) {
		List<Vehicle> vehicles = new ArrayList<>();
		this.vehicleRepository.findByOwnerId(ownerId).forEach(vehicles::add);
		return vehicles;
	}
	
	@GetMapping("owners/{ownerId}/vehicles/{id}")
	public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id : " + vehicleId));
		return ResponseEntity.ok().body(vehicle);
	}
	
	@PostMapping("/owners/{ownerId}/vehicles")
	public Vehicle createVehicle(@RequestBody Vehicle vehicle, @PathVariable(value = "ownerId") Long ownerId) throws ResourceNotFoundException  {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		Vehicle vehicle1 = new Vehicle();
		vehicle1.setOwner(owner);
		vehicle1.setModel(vehicle.getModel());
		vehicle1.setRegNo(vehicle.getRegNo());
		return this.vehicleRepository.save(vehicle1);
	}
	
	@PutMapping("/vehicles/{id}")
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable(value = "id") Long vehicleId, @Validated @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException{
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setRegNo(vehicleDetails.getRegNo());
		return ResponseEntity.ok(this.vehicleRepository.save(vehicle));
	}
	
	@DeleteMapping("/vehicles/{id}")
	public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		this.vehicleRepository.delete(vehicle);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
