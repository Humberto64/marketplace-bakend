package com.troyecto.marketplace.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Double subtotal;
    private Integer totalAmount;
    private Integer tax;
    private String currency;
    private String payMethod;
    private String paymentStatus;
    private LocalDateTime orderDate;

    private Long userId;
    private String userName;
    // Comentario:
    // - El DTO contiene userId en lugar de la entidad User completa para evitar exponer la entidad JPA y facilitar la serialización.
    // - userName es útil para mostrar información legible en respuestas sin cargar toda la entidad User.

    private List<OrderItemDTO> orderItems;
    // Comentario:
    // - Representa los items de la orden como DTOs. Mantener DTOs evita problemas de serialización y acoplamiento directo a entidades.
}
