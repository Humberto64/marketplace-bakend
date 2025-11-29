package com.troyecto.marketplace.dtos.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "First Name cannot be null")
    private String firstName;
    @NotBlank(message = "Last Name cannot be null")
    private String lastName;
    // Nota: usar Long para phone puede estar bien, pero en muchos casos es mejor String
    // para preservar ceros a la izquierda y símbolos internacionales.
    @NotNull(message = "Phone number cannot be null")
    @Size( min = 8, max = 20, message = "Phone must be in between 8 and 20 digits")
    private Long phone;
    @NotBlank(message = "Address cannot be null")
    private String address;
    @NotBlank(message = "Email cannot be null")
    @Email(message = "Must provide a valid email")
    private String email;
    @NotBlank(message = "Role cannot be null")
    private String role;
}
