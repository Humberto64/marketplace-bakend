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
}