package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Register;

import java.util.List;

public interface RegisterService {
    void registerExit(String plate);

    void registerEntry(String plate);

    List<Register> getAllRegisters();
}
