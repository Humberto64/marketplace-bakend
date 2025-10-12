package com.troyecto.marketplace.dtos;

import lombok.Data;
// 🔑 IMPORTACIONES NECESARIAS PARA QUE FUNCIONE:
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Data
public class StoreDTO {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String name;

    private String description;

    @NotBlank(message = "La categoría es obligatoria.")
    private String category;

    @NotNull(message = "El ID del dueño (vendedor) es obligatorio.")
    private Long ownerId;
}