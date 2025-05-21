package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Cell;
import com.parking.autoscolombia.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CellServiceImpl implements CellService {

    @Autowired
    private CellRepository cellRepository;

    @Override
    public List<Cell> getAllCells() {
        return cellRepository.findAll();
    }

    @Override
    public void saveCell(Cell cell) {
        cellRepository.save(cell);
    }

    @Override
    public Optional<Cell> getFreeCell() {
        return cellRepository.findFirstByOccupiedFalse();
    }

    @Override
    public void setCellOccupied(Cell cell, boolean occupied) {
        cell.setOccupied(occupied);
        cellRepository.save(cell);
    }
}
