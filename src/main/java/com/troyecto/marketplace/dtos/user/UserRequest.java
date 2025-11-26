package com.troyecto.marketplace.dtos.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String firstName;
    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;
    // Nota: usar Long para phone puede estar bien, pero en muchos casos es mejor String
    // para preservar ceros a la izquierda y símbolos internacionales.
    @NotNull(message = "El teléfono es obligatorio")
    private Long phone;
    @NotBlank(message = "La dirección es obligatoria")
    private String address;
    @NotBlank(message = "El correo es obligatorio")
    private String email;
    @NotBlank(message = "El rol es obligatorio")
    private String role;
}
