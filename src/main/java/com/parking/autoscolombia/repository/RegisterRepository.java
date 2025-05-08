package com.parking.autoscolombia.repository;

import com.parking.autoscolombia.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    List<Register> findByVehiclePlate(String plate);

    Optional<Register> findTopByVehiclePlateAndExitDateIsNullOrderByEntryDateDesc(String plate);
}
