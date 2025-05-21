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

}
