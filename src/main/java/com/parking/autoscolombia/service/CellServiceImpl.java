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
        List<Cell> cells = cellRepository.findAll();
        for (Cell c : cells) {
            if (c.getCode() == cell.getCode()) {
                throw new RuntimeException("Ya existe una celda con el mismo código");
            }
        }
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

    @Override
    public Optional<Cell> getCellById(Long id) {
        return cellRepository.findById(id);
    }

    @Override
    public void deleteCell(Cell cell) {
        cellRepository.delete(cell);
    }
}
