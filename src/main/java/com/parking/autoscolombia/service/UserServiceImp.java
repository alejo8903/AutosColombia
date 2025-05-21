package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.User;
import com.parking.autoscolombia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (userRepository.existsById(user.getCedula())) {
            throw new IllegalArgumentException("La cédula ya está registrada.");
        }
        if (user.getEstado() == null || user.getEstado().isBlank()) {
            user.setEstado("Activo");
        }
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(String cedula) {
        return userRepository.findById(cedula);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String cedula) {
        Optional<User> userOpt = userRepository.findById(cedula);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            if(!"Activo".equalsIgnoreCase(user.getEstado())) {
                userRepository.delete(user);
            } else {
                throw new IllegalStateException("No se puede eliminar un usuario activo.");
            }
        }
    }
}

