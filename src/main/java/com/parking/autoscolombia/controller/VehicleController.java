package com.parking.autoscolombia.controller;

import com.parking.autoscolombia.model.Register;
import com.parking.autoscolombia.model.Vehicle;
import com.parking.autoscolombia.service.RegisterService;
import com.parking.autoscolombia.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final RegisterService registerService;
    // Mostrar formulario de entrada
    @GetMapping("/entrada")
    public String mostrarFormularioEntrada(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "ingress";
    }

    // Procesar entrada de vehículo
    @PostMapping("/entrada")
    public String registrarEntrada(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicleIfNotExists(vehicle);
        registerService.registerEntry(vehicle.getPlate());
        return "redirect:/vehicle/historial";
    }

    // Mostrar formulario de salida
    @GetMapping("/salida")
    public String mostrarFormularioSalida(Model model) {
        model.addAttribute("placa", "");
        return "exit";
    }

    // Procesar salida de vehículo
    @PostMapping("/salida")
    public String registrarSalida(@RequestParam String plate) {
        registerService.registerExit(plate);
        return "redirect:/vehicle/historial";
    }

    // Mostrar historial de registros
    @GetMapping("/historial")
    public String verHistorial(Model model) {
        List<Register> registers = registerService.getAllRegisters();
        model.addAttribute("registers", registers);
        return "history";
    }


}
