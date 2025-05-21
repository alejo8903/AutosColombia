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


    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/list";
    }


    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }


    @PostMapping
    public String createUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/create";
        }
        try {
            userService.saveUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user); 
            return "users/create";
        }
        return "redirect:/users";
    }


    @GetMapping("/edit/{cedula}")
    public String showEditForm(@PathVariable String cedula, Model model) {
        User user = userService.getUserById(cedula)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        model.addAttribute("user", user);
        return "users/edit";
    }


    @PostMapping("/update/{cedula}")
    public String updateUser(@PathVariable String cedula, @Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/edit";
        }
        try {

            if (!cedula.equals(user.getCedula())) {
                if (userService.getUserById(user.getCedula()).isPresent()) {
                    model.addAttribute("errorMessage", "La cédula ya está registrada por otro usuario.");
                    model.addAttribute("user", user);
                    return "users/edit";
                }
            }
            user.setCedula(cedula); 
            userService.updateUser(user);
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", user);
            return "users/edit";
        }
        return "redirect:/users";
    }


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


