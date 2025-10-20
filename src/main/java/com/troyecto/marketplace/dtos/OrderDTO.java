package com.troyecto.marketplace.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private Long items;     //Cambiar despues a List<orderItems>
    private BigDecimal subtotal;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private String currency;
    private String payMethod;
    private String paymentStatus;
    private LocalDateTime orderDate;
}
