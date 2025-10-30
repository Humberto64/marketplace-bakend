package com.troyecto.marketplace.dtos;

import com.troyecto.marketplace.entities.Product;
import lombok.Data;
// 🔑 IMPORTACIONES NECESARIAS PARA QUE FUNCIONE:
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StoreDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String name;
    private String description;
    @NotBlank(message = "La categoría es obligatoria.")
    private String category;
    private LocalDateTime createdDate;
    private Boolean isActive;
    private Long userId;
    private String userName;
    private List<ProductDTO> products;

    // Comentarios:
    // - @NotBlank / @Size: validaciones que se aplican típicamente cuando se usa @Valid en el controlador.
    // - userId/userName: el DTO expone sólo el id de usuario y el nombre legible, no la entidad User entera.
    // - La lista 'products' usa ProductDTO para evitar exponer la entidad Product en la API.
    // - Importar la entidad Product aquí no es necesario; el DTO debe usar otros DTOs (ProductDTO).
}