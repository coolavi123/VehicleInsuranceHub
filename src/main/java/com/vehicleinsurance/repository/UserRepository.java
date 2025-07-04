package com.vehicleinsurance.repository;

import com.vehicleinsurance.entity.User;



import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	

}
