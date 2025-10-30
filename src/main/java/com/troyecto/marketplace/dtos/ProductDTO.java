package com.troyecto.marketplace.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
// 🔑 IMPORTACIONES NECESARIAS PARA QUE FUNCIONE:

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private LocalDateTime publishedDate;
    private Boolean isAvailable;

    private Long storeId;
    private String storeName;
    // - storeId/storeName: se usan para representar la relación con Store en el DTO sin exponer la entidad.
    // - Evita enviar la entidad Store completa en las respuestas.

    private List<OrderItemDTO> orderItem;
    private List<ReviewDTO> reviews;

}