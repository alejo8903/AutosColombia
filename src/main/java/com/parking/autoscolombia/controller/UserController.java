package com.parking.autoscolombia.controller;

import com.parking.autoscolombia.model.User;
import com.parking.autoscolombia.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // Mostrar lista de usuarios
    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }

    // Mostrar formulario para crear nuevo usuario
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    // Procesar creación de nuevo usuario
    @PostMapping
    public String createUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/create";
        }
        try {
            userService.saveUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user); // Mantener datos en formulario
            return "users/create";
        }
        return "redirect:/users";
    }

    // Mostrar formulario para editar usuario
    @GetMapping("/edit/{cedula}")
    public String showEditForm(@PathVariable String cedula, Model model) {
        User user = userService.getUserById(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "users/edit";
    }

    // Procesar actualización de usuario
    @PostMapping("/update/{cedula}")
    public String updateUser(@PathVariable String cedula, @Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        try {
            // Opcional: validar que no exista otro usuario con la misma cédula si cambió
            if (!cedula.equals(user.getCedula())) {
                if (userService.getUserById(user.getCedula()).isPresent()) {
                    model.addAttribute("errorMessage", "La cédula ya está registrada por otro usuario.");
                    model.addAttribute("user", user);
                    return "users/edit";
                }
            }
            user.setCedula(cedula); // Aseguramos la cédula original para la actualización
            userService.updateUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user);
            return "users/edit";
        }
        return "redirect:/users";
    }

    // Eliminar usuario si no está activo
    @GetMapping("/delete/{cedula}")
    public String deleteUser(@PathVariable String cedula, Model model) {
        try {
            userService.deleteUser(cedula);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return listUsers(model);
        }
        return "redirect:/users";
    }
}


