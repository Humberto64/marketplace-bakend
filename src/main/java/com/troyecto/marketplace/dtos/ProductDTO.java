package com.troyecto.marketplace.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
// 🔑 IMPORTACIONES NECESARIAS PARA QUE FUNCIONE:

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
    private Boolean isAvailable;
    private List<OrderItemDTO> orderItem;
    private List<ReviewDTO> reviews;
}