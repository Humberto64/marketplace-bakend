package com.troyecto.marketplace.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "La descripción es obligatoria")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio")
    @Positive(message = "El stock debe ser mayor o igual a cero")
    private Integer stock;

    private LocalDateTime publishedDate;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean isAvailable;

    // Lo importante para crear el Product es el storeId.
    @NotNull(message = "La tienda es obligatoria")
    private Long storeId;

    // Este campo normalmente no es necesario en el request, pero si quieres permitir que te lo manden, lo dejas opcional
    private String storeName;
}
