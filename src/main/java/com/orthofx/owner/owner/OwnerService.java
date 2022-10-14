package com.orthofx.owner.owner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.orthofx.owner.exception.ResourceNotFoundException;
import com.orthofx.owner.vehicle.Vehicle;
import com.orthofx.owner.vehicle.VehicleDTO;
import com.orthofx.owner.vehicle.VehicleRepository;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	private OwnerDTO ownerETD(Owner owner) {
		OwnerDTO ownerDTO = new OwnerDTO();
		ownerDTO.setId(owner.getId());
		ownerDTO.setName(owner.getName());
		ownerDTO.setPhoneNumber(owner.getPhoneNumber());
		return ownerDTO;
	}
	
	private Owner ownerDTE(OwnerDTO ownerDTO) {
		Owner owner = new Owner();
		owner.setId(ownerDTO.getId());
		owner.setName(ownerDTO.getName());
		owner.setPhoneNumber(ownerDTO.getPhoneNumber());
		return owner;
	}
	
	private VehicleDTO vehicleETD(Vehicle vehicle) {
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setId(vehicle.getId());
		vehicleDTO.setModel(vehicle.getModel());
		vehicleDTO.setRegNo(vehicle.getRegNo());
		return vehicleDTO;
	}

	//get all owners
	public List<OwnerDTO> getAllOwners() {
		return ownerRepository.findAll().stream().map(this::ownerETD).collect(Collectors.toList());
	}

	//get owner by Id
	public ResponseEntity<OwnerDTO> getOwnerById(Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id : " + ownerId));
		OwnerDTO ownerDTO = ownerETD(owner);
		return ResponseEntity.ok().body(ownerDTO);
	}

	//create owner
	public Owner createOwner(@RequestBody OwnerDTO ownerDTO) {
		Owner owner = ownerDTE(ownerDTO);
		return this.ownerRepository.save(owner);
	}

	//update owner by Id
	public ResponseEntity<OwnerDTO> updateOwner(Long ownerId, Owner ownerDetails) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		owner.setName(ownerDetails.getName());
		owner.setPhoneNumber(ownerDetails.getPhoneNumber());
		OwnerDTO ownerDTO = ownerETD(owner);
		this.ownerRepository.save(owner);
		return ResponseEntity.ok(ownerDTO);
	}

	//delete owner by Id
	public Map<String, Boolean> deleteOwner(Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));

		this.ownerRepository.delete(owner);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	//get all vehicles of an owner
	public List<VehicleDTO> getAllVehicles(Long ownerId) {
		List<VehicleDTO> vehicles = new ArrayList<>();
		this.vehicleRepository.findByOwnerId(ownerId).stream().map(this::vehicleETD).collect(Collectors.toList());
		return vehicles;
	}

	//get a vehicle of an owner
	public ResponseEntity<VehicleDTO> getVehicleById(Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id : " + vehicleId));
		VehicleDTO vehicleDTO = vehicleETD(vehicle);
		return ResponseEntity.ok().body(vehicleDTO);
	}

	//create a vehicle for an owner
	public Vehicle createVehicle(VehicleDTO vehicleDetails, Long ownerId) throws ResourceNotFoundException {
		Owner owner = ownerRepository.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner not found for this id :: " + ownerId));
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner(owner);
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setRegNo(vehicleDetails.getRegNo());
		return this.vehicleRepository.save(vehicle);
	}
}
