package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.Insurance;
import com.vehicleinsurance.entity.Vehicle;
import com.vehicleinsurance.service.InsuranceService;
import com.vehicleinsurance.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/insurances")
    public String listInsurances(Model model) {
        List<Insurance> insurances = insuranceService.getAllInsurances();
        model.addAttribute("insurances", insurances);
        return "insurances";
    }

    @GetMapping("/insurances/add")
    public String showAddInsuranceForm(Model model) {
        model.addAttribute("insurance", new Insurance());
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        return "add_insurance";
    }

    @PostMapping("/insurances/add")
    public String addInsurance(Insurance insurance) {
        insuranceService.saveInsurance(insurance);
        return "redirect:/insurances";
    }
}
