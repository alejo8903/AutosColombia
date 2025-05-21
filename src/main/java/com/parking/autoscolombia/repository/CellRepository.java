package com.parking.autoscolombia.repository;


import com.parking.autoscolombia.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CellRepository extends JpaRepository<Cell, Long> {
    Optional<Cell> findFirstByOccupiedFalse();
}
