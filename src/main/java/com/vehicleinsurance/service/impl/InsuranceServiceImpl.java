package com.vehicleinsurance.service.impl;

import com.vehicleinsurance.entity.Insurance;
import com.vehicleinsurance.repository.InsuranceRepository;
import com.vehicleinsurance.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll();
    }
}
