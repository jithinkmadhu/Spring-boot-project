package com.orthofx.owner.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDTO, Long> {

	public List<VehicleDTO> findByOwnerId(Long ownerId);
}
