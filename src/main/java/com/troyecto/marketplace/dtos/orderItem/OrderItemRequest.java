package com.troyecto.marketplace.dtos.orderItem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRequest {

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Double subtotal;

    @NotNull
    private Long orderId;

    @NotNull
    private Long productId;
    private String productName;
}
