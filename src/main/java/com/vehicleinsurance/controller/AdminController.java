package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.User;
import com.vehicleinsurance.entity.Vehicle;
import com.vehicleinsurance.entity.Insurance;
import com.vehicleinsurance.service.UserService;
import com.vehicleinsurance.service.VehicleService;
import com.vehicleinsurance.service.InsuranceService;
import com.vehicleinsurance.service.PolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private InsuranceService insuranceService;
    
    @Autowired
    private PolicyService policyService;

    @GetMapping("/policies")
    public String viewAllPolicies(Model model) {
        model.addAttribute("policies", policyService.getAllPolicies());
        return "admin_policy_list";
    }
    
    @GetMapping("/admin/view-users")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin_users"; // Ensure admin_users.html exists in templates
    }

    @GetMapping("/admin/manage-vehicles")
    public String manageVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicles());
        return "admin_vehicles"; // Ensure admin_vehicles.html exists in templates
    }

    


    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        long userCount = userService.getAllUsers().size();
        long vehicleCount = vehicleService.getAllVehicles().size();
        long insuranceCount = insuranceService.getAllInsurances().size();

        model.addAttribute("userCount", userCount);
        model.addAttribute("vehicleCount", vehicleCount);
        model.addAttribute("insuranceCount", insuranceCount);

        return "admin_dashboard";
    }

    @GetMapping("/admin/users")
    public String listAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin_users";
    }

    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/vehicles")
    public String listAllVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "admin_vehicles";
    }

    @GetMapping("/admin/insurances")
    public String listAllInsurances(Model model) {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        model.addAttribute("insurances", insurances);
        return "admin_insurances";
    }
}
