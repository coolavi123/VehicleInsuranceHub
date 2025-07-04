package com.vehicleinsurance.service;

import com.vehicleinsurance.entity.Vehicle;
import java.util.List;

public interface VehicleService {
    Vehicle saveVehicle(Vehicle vehicle);
    List<Vehicle> getAllVehicles();
}
