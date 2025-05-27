package com.parking.autoscolombia.repository;

import com.parking.autoscolombia.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser_Cedula(String cedula);
}
