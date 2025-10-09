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
    @Column(nullable = false)
    private String orderNumber;
    @Column(nullable = false)
    private String buyer;   //Cambiar despues a ManytoOne con user
    @Column(nullable = false)
    private String seller;  //Cambiar despues a ManytoOne con user
    @Column(nullable = false)
    private Long items;     //Cambiar despues a List<orderItems>
    @Column(nullable = false)
    private BigDecimal subtotal;
    @Column(nullable = false)
    private BigDecimal totalAmount;
    @Column(nullable = false)
    private BigDecimal tax;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private String payMethod;
    @Column(nullable = false)
    private String paymentStatus;
    @Column(nullable = false)
    private LocalDateTime orderDate;
}
