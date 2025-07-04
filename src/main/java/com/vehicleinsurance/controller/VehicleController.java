package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.Vehicle;
import com.vehicleinsurance.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicles")
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "vehicles";
    }

    @GetMapping("/vehicles/add")
    public String showAddVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "add_vehicle";
    }

    @PostMapping("/vehicles/add")
    public String addVehicle(Vehicle vehicle) {
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }
}
