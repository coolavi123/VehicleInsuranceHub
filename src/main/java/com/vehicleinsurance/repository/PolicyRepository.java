package com.vehicleinsurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vehicleinsurance.entity.Policy;
import com.vehicleinsurance.service.PolicyService;

public interface PolicyRepository extends JpaRepository<Policy, Long> {
	List<Policy> findByVehicleType(String vehicleType);
	
}

