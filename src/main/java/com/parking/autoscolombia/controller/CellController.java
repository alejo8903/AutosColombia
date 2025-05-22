package com.parking.autoscolombia.controller;

import com.parking.autoscolombia.model.Cell;
import com.parking.autoscolombia.service.CellService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/celdas")
@AllArgsConstructor
public class CellController {

    private final CellService cellService;

    @GetMapping
    public String listarCeldas(Model model) {
        model.addAttribute("celdas", cellService.getAllCells());
        return "cell/list";
    }

    @GetMapping("/nueva")
    public String nuevaCelda(Model model) {
        model.addAttribute("cell", new Cell());
        return "cell/form";
    }

    @PostMapping("/guardar")
    public String guardarCelda(@ModelAttribute Cell cell) {
        cellService.saveCell(cell);
        return "redirect:/celdas";
    }

    @GetMapping("/editar/{id}")
    public String editarCelda(@PathVariable Long id, Model model) {
        Cell cell = cellService.getCellById(id).orElseThrow(() -> new IllegalArgumentException("ID no válido"));
        model.addAttribute("cell", cell);
        return "cell/form"; // Reutilizar el formulario
    }

    @PostMapping("/actualizar")
    public String actualizarCelda(@ModelAttribute Cell cell) {
        Cell existingCell = cellService.getCellById(cell.getId()).orElseThrow();
        existingCell.setOccupied(cell.isOccupied()); // Solo campos editables
        cellService.saveCell(existingCell);
        return "redirect:/celdas";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarCelda(@PathVariable Long id) {
        Cell cell = cellService.getCellById(id).orElseThrow(() -> new IllegalArgumentException("ID no válido"));
        cellService.setCellOccupied(cell, false); // Desocupar la celda
        cellService.deleteCell(cell); // Eliminar la celda
        return "redirect:/celdas";
    }

}
