package com.orthofx.owner.owner;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthofx.owner.exception.ResourceNotFoundException;
import com.orthofx.owner.vehicle.Vehicle;
import com.orthofx.owner.vehicle.VehicleDTO;

@RestController
@RequestMapping("/api/v1/")
public class OwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	//get all owners
	@GetMapping("/owners")
	public List<OwnerDTO> getAllOwners() {
		return this.ownerService.getAllOwners();
	}
	
	//get owner by Id
	@GetMapping("/owners/{id}")
	public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable(value = "id") Long ownerId) throws ResourceNotFoundException{
		return this.ownerService.getOwnerById(ownerId);
	}
	
	//create owner
	@PostMapping("/owners")
	public Owner createOwner(@RequestBody OwnerDTO owner) {
		return this.ownerService.createOwner(owner);
	}
	
	//update owner by Id
	@PutMapping("/owners/{id}")
	public ResponseEntity<OwnerDTO> updateOwner(@PathVariable(value = "id") Long ownerId, @Validated @RequestBody OwnerDTO ownerDetails) throws ResourceNotFoundException{
		return this.ownerService.updateOwner(ownerId, ownerDetails);
	}
	
	//delete owner by Id
	@DeleteMapping("/owners/{id}")
	public Map<String, Boolean> deleteOwner(@PathVariable(value = "id") Long ownerId) throws ResourceNotFoundException {
		return this.ownerService.deleteOwner(ownerId);
	}
	
	//get all vehicles of an owner
	@GetMapping("owners/{ownerId}/vehicles")
	public List<VehicleDTO> getAllVehicles(@PathVariable Long ownerId) throws ResourceNotFoundException {
		return this.ownerService.getAllVehicles(ownerId);
	}
	
	//get a vehicle of an owner
	@GetMapping("owners/{ownerId}/vehicles/{id}")
	public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
		return this.ownerService.getVehicleById(vehicleId);
	}
	
	//create a vehicle for an owner
	@PostMapping("/owners/{ownerId}/vehicles")
	public Vehicle createVehicle(@RequestBody VehicleDTO vehicle, @PathVariable(value = "ownerId") Long ownerId) throws ResourceNotFoundException  {
		return this.ownerService.createVehicle(vehicle, ownerId);
	}
	
}
