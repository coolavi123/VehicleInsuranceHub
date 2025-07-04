package com.vehicleinsurance.service.impl;

import com.vehicleinsurance.entity.Policy;
import com.vehicleinsurance.repository.PolicyRepository;
import com.vehicleinsurance.service.PolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }
}
