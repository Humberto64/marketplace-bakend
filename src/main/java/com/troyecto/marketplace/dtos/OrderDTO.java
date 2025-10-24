package com.troyecto.marketplace.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private BigDecimal subtotal;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private String currency;
    private String payMethod;
    private String paymentStatus;
    private LocalDateTime orderDate;

    private Long userId;
    private String userName;

    private List<OrderItemDTO> orderItems;
}
