package com.troyecto.marketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double subtotal;

    //private String order; // cambiar por una relacion con order
    //private String product; //cambiar por una relacion con product
}
