package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(String cedula);
    User updateUser(User user);
    void deleteUser(String cedula);
}
