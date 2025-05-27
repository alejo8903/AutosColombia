package com.parking.autoscolombia.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Double amount;

    private String method; // "Efectivo", "Transferencia", etc.

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
