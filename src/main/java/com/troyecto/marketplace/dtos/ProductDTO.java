package com.troyecto.marketplace.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// 🔑 IMPORTACIONES NECESARIAS PARA QUE FUNCIONE:
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime publishedDate;
    private boolean isAvailable;
}