package com.parking.autoscolombia.repository;

import com.parking.autoscolombia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

