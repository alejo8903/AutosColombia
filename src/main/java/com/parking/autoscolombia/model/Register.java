package com.parking.autoscolombia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Integer totalTime;
    @ManyToOne
    @JoinColumn(name = "vehicle_plate", referencedColumnName = "plate")
    private Vehicle vehicle;
    @ManyToOne
    private Cell cell;
}
