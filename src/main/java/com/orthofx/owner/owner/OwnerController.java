package com.orthofx.owner.owner;

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

@RestController
@RequestMapping("/api/v1/")
public class OwnerController {
	
	@Autowired
	private OwnerRepository ownerRepository;
	
	
	@GetMapping("/owners")
	public List<Owner> getAllOwners() {
		return this.ownerRepository.findAll();
	}
	
	@GetMapping("/owners/{id}")
	public ResponseEntity<Owner> getOwnerById(@PathVariable(value = "id") Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		return ResponseEntity.ok().body(owner);
	}
	
	@PostMapping("/owners")
	public Owner createOwner(@RequestBody Owner owner) {
		return this.ownerRepository.save(owner);
	}
	
	@PutMapping("/owners/{id}")
	public ResponseEntity<Owner> updateOwner(@PathVariable(value = "id") Long ownerId, @Validated @RequestBody Owner ownerDetails) throws ResourceNotFoundException{
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		owner.setName(ownerDetails.getName());
		owner.setPhoneNumber(ownerDetails.getPhoneNumber());
		owner.setVehicle(ownerDetails.getVehicle());
		return ResponseEntity.ok(this.ownerRepository.save(owner));
	}
	
	@DeleteMapping("/owners/{id}")
	public Map<String, Boolean> deleteOwner(@PathVariable(value = "id") Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		
		this.ownerRepository.delete(owner);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
