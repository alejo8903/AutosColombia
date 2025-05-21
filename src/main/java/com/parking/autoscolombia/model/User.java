package com.parking.autoscolombia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    @Size(min = 6, message = "La cédula debe tener mínimo 6 dígitos")
    private String cedula;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d+", message = "El teléfono debe tener solo números")
    private String telefono;

    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @NotBlank(message = "El estado es obligatorio") 
    private String estado;

    // Método para obtener el estado como booleano
    public boolean isActivo() {
        return "Activo".equalsIgnoreCase(this.estado);
    }

    // Método para setear estado a partir de booleano
    public void setActivo(boolean activo) {
        this.estado = activo ? "Activo" : "Inactivo";
    }
}

