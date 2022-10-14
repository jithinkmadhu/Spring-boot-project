package com.orthofx.owner.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orthofx.owner.exception.ResourceNotFoundException;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	private VehicleDTO vehicleETD(Vehicle vehicle) {
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setId(vehicle.getId());
		vehicleDTO.setModel(vehicle.getModel());
		vehicleDTO.setRegNo(vehicle.getRegNo());
		return vehicleDTO;
	}
	
	public List<VehicleDTO> getAllVehicles() {
		return this.vehicleRepository.findAll().stream().map(this::vehicleETD).collect(Collectors.toList());
	}
	
	public ResponseEntity<VehicleDTO> updateVehicle(Long vehicleId, VehicleDTO vehicleDetails) throws ResourceNotFoundException{
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setRegNo(vehicleDetails.getRegNo());
		this.vehicleRepository.save(vehicle);
		return ResponseEntity.ok(vehicleDetails);
	}
	
	public Map<String, Boolean> deleteVehicle(Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		this.vehicleRepository.delete(vehicle);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
