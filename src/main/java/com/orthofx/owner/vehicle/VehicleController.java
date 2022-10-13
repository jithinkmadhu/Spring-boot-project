package com.orthofx.owner.vehicle;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orthofx.owner.exception.ResourceNotFoundException;


@RestController
@RequestMapping("/api/v1")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	//get all vehicles
	@GetMapping("/vehicles")
	public List<VehicleDTO> getAllVehicles() {
		return this.vehicleService.getAllVehicles();
	}
	
//	update a vehicle by id
	@PutMapping("/vehicles/{id}")
	public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable(value = "id") Long vehicleId, @Validated @RequestBody VehicleDTO vehicleDetails) throws ResourceNotFoundException{
		return this.vehicleService.updateVehicle(vehicleId, vehicleDetails);
	}
	
//	delete a vehicle by id
	@DeleteMapping("/vehicles/{id}")
	public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
		return this.vehicleService.deleteVehicle(vehicleId);
	}
	
}
