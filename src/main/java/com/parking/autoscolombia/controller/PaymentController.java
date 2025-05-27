package com.parking.autoscolombia.controller;

import com.parking.autoscolombia.model.Payment;
import com.parking.autoscolombia.model.User;
import com.parking.autoscolombia.service.PaymentService;
import com.parking.autoscolombia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/pagos")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final UserService userService;

    @GetMapping
    public String listarPagos(Model model) {
        model.addAttribute("pagos", paymentService.getAllPayments());
        return "payments/list";
    }

    @GetMapping("/nuevo")
    public String nuevoPago(Model model) {
        model.addAttribute("pago", new Payment());
        model.addAttribute("usuarios", userService.getAllUsers());
        return "payments/form";
    }

    @PostMapping("/guardar")
    public String guardarPago(@ModelAttribute Payment pago) {
        pago.setDate(LocalDate.now());

        // Validación adicional
        if (pago.getUser() == null) {
            throw new IllegalArgumentException("Debe seleccionar un usuario.");
        }

        paymentService.savePayment(pago);
        return "redirect:/pagos";
    }

    @GetMapping("/historial/{cedula}")
    public String historialPagos(@PathVariable String cedula, Model model) {
        List<Payment> pagos = paymentService.getPaymentsByUser(cedula);
        model.addAttribute("pagos", pagos);
        return "payments/historial";
    }
}
