package com.vehicleinsurance.controller;

import com.vehicleinsurance.entity.Policy;
import com.vehicleinsurance.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminPolicyController {

    @Autowired
    private PolicyRepository policyRepository;

    @GetMapping("/policies")
    public String viewPolicies(Model model) {
        model.addAttribute("policies", policyRepository.findAll());
        return "admin_policies";
    }

    @GetMapping("/add-policy")
    public String showAddPolicyForm(Model model) {
        model.addAttribute("policy", new Policy());
        return "add_policy";
    }

    @PostMapping("/add-policy")
    public String addPolicy(@ModelAttribute Policy policy) {
        policyRepository.save(policy);
        return "redirect:/admin/policies";
    }
}
