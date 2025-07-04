package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.User;
import com.vehicleinsurance.repository.UserRepository;
import com.vehicleinsurance.service.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;

    public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
    private UserService userService;
	
	

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        User existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }
        userService.saveUser(user);
        model.addAttribute("success", "Registration successful, please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
