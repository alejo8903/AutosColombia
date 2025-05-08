package com.parking.autoscolombia.repository;

import com.parking.autoscolombia.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {


}
