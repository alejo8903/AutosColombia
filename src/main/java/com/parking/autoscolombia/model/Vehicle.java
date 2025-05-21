package com.parking.autoscolombia.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Vehicle {

    @Id
    private String plate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
