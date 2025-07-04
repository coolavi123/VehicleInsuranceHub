package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.Policy;
import com.vehicleinsurance.entity.Vehicle;
import com.vehicleinsurance.entity.Insurance;
import com.vehicleinsurance.repository.PolicyRepository;
import com.vehicleinsurance.repository.VehicleRepository;
import com.vehicleinsurance.repository.InsuranceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    /**
     * Show vehicle type selection page before showing policies
     */
    @GetMapping("/buy-insurance")
    public String selectVehicleTypeForPolicy() {
        return "select_vehicle_type";
    }

    /**
     * Show policies filtered by vehicle type
     */
    @GetMapping("/buy-insurance/{vehicleType}")
    public String showPoliciesByVehicleType(@PathVariable String vehicleType, Model model) {
        List<Policy> policies = policyRepository.findByVehicleType(vehicleType);
        model.addAttribute("policies", policies);
        model.addAttribute("vehicleType", vehicleType);
        return "buy_insurance";
    }

    /**
     * Handle policy purchase by user
     */
    @GetMapping("/buy-policy/{policyId}/{vehicleId}")
    public String buyPolicy(@PathVariable Long policyId, @PathVariable Long vehicleId, Model model) {
        Optional<Policy> policyOpt = policyRepository.findById(policyId);
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(vehicleId);

        if (policyOpt.isPresent() && vehicleOpt.isPresent()) {
            Policy policy = policyOpt.get();
            Vehicle vehicle = vehicleOpt.get();

            Insurance insurance = new Insurance();
            insurance.setPolicyNumber("POL" + System.currentTimeMillis());
            insurance.setInsuranceCompany(policy.getName());
            insurance.setStartDate(LocalDate.now().toString());
            insurance.setEndDate(LocalDate.now().plusYears(1).toString());
            insurance.setPremiumAmount(policy.getPremiumAmount());
            insurance.setVehicle(vehicle);
            insurance.setPolicy(policy);

            insuranceRepository.save(insurance);

            model.addAttribute("insurance", insurance);
            return "payment_gateway";
        } else {
            return "redirect:/dashboard";
        }
    }

    /**
     * View purchased insurances for the user
     */
    @GetMapping("/purchased-policies")
    public String viewPurchasedPolicies(Model model) {
        List<Insurance> insurances = insuranceRepository.findAll();
        model.addAttribute("insurances", insurances);
        return "purchased_policies";
    }
}
