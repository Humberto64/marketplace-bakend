package com.troyecto.marketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private String buyer;   //Cambiar despues a ManytoOne con user
    private String seller;  //Cambiar despues a ManytoOne con user
    private Long items;     //Cambiar despues a List<orderItems>
    private BigDecimal subtotal;
    private BigDecimal totalAmount;
    private BigDecimal tax;
    private String currency;
    private String payMethod;
    private String paymentStatus;
    private LocalDateTime orderDate;
}
