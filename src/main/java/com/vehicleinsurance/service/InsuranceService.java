package com.vehicleinsurance.service;

import com.vehicleinsurance.entity.Insurance;
import java.util.List;

public interface InsuranceService {
    Insurance saveInsurance(Insurance insurance);
    List<Insurance> getAllInsurances();
}
