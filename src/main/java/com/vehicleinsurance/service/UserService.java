package com.vehicleinsurance.service;

import java.util.List;

import com.vehicleinsurance.entity.User;

public interface UserService {
	
    User saveUser(User user);
    User getUserByEmail(String email);
    
    List<User> getAllUsers();
    void deleteUserById(Long id);

}
