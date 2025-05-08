package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Vehicle;
import com.parking.autoscolombia.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImp implements VehicleService {


    @Autowired
    private VehicleRepository vehiculoRepository;

    @Override
    public void saveVehicleIfNotExists(Vehicle vehicle) {
        if (!vehiculoRepository.existsById(vehicle.getPlate())) {
            vehiculoRepository.save(vehicle);
        }
    }
}
