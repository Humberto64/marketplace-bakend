package com.troyecto.marketplace.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private int  quantity;
    private double price;
    private double subtotal;
}
