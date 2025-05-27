package com.parking.autoscolombia.service;


import com.parking.autoscolombia.model.Payment;

import java.util.List;

public interface PaymentService {
    Object getAllPayments();

    void savePayment(Payment pago);

    List<Payment> getPaymentsByUser(String cedula);
}
