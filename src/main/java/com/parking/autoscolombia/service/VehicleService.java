package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Vehicle;

public interface VehicleService {
    void saveVehicleIfNotExists(Vehicle vehicle);
}
