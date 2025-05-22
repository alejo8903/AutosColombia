package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Cell;

import java.util.List;
import java.util.Optional;

public interface CellService {
    List<Cell> getAllCells();
    void saveCell(Cell cell);
    Optional<Cell> getFreeCell();
    void setCellOccupied(Cell cell, boolean occupied);

    Optional<Cell> getCellById(Long id);

    void deleteCell(Cell cell);
}
