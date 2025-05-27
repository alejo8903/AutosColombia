package com.parking.autoscolombia.service;

import com.parking.autoscolombia.model.Payment;
import com.parking.autoscolombia.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByUser(String cedula) {
        return paymentRepository.findByUser_Cedula(cedula);
    }
}
