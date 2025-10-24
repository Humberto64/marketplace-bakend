package com.troyecto.marketplace.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private int  quantity;
    private double price;
    private double subtotal;

    private Long orderId;

    private Long productId;
    private String productName;
}
