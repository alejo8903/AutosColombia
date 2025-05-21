package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Cell;
import com.parking.autoscolombia.model.Register;
import com.parking.autoscolombia.model.Vehicle;
import com.parking.autoscolombia.repository.RegisterRepository;
import com.parking.autoscolombia.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService{

    @Autowired
    private RegisterRepository registroRepository;

    @Autowired
    private VehicleRepository vehiculoRepository;

    @Autowired
    private CellService cellService;

    @Override
    public void registerEntry(String plate) {
        Vehicle vehicle = vehiculoRepository.findById(plate).orElseThrow();
        Cell cell = cellService.getFreeCell().orElseThrow(() -> new RuntimeException("No hay celdas disponibles"));

        Register register = new Register();
        register.setVehicle(vehicle);
        register.setEntryDate(LocalDateTime.now());
        register.setCell(cell);

        cellService.setCellOccupied(cell, true);
        registroRepository.save(register);
    }

    @Override
    public void registerExit(String plate) {
        Register register = registroRepository.findTopByVehiclePlateAndExitDateIsNullOrderByEntryDateDesc(plate)
                .orElseThrow(() -> new RuntimeException("No se encontró entrada activa"));

        register.setExitDate(LocalDateTime.now());
        Duration duracion = Duration.between(register.getEntryDate(), register.getExitDate());
        register.setTotalTime((int) duracion.toMinutes());

        cellService.setCellOccupied(register.getCell(), false);
        registroRepository.save(register);
    }

    @Override
    public List<Register> getAllRegisters() {
        return registroRepository.findAll();
    }
}
