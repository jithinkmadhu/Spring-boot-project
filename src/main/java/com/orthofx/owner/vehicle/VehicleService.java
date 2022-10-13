package com.orthofx.owner.vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orthofx.owner.exception.ResourceNotFoundException;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Vehicle> getAllVehicles() {
		return this.vehicleRepository.findAll();
	}
	
	public ResponseEntity<Vehicle> updateVehicle(Long vehicleId, Vehicle vehicleDetails) throws ResourceNotFoundException{
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		vehicle.setModel(vehicleDetails.getModel());
		vehicle.setRegNo(vehicleDetails.getRegNo());
		return ResponseEntity.ok(this.vehicleRepository.save(vehicle));
	}
	
	public Map<String, Boolean> deleteVehicle(Long vehicleId) throws ResourceNotFoundException {
		Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
		
		this.vehicleRepository.delete(vehicle);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
