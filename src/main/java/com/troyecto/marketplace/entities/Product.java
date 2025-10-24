package com.troyecto.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data // Incluye Getters, Setters, etc. (Lombok)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products") // Mapea a una tabla llamada 'products'
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer stock;
    //@Column(nullable = false)
    private LocalDateTime publishedDate;
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.EAGER,optional = false)
    @JoinColumn(name = "product_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_product_user"))
    @JsonBackReference
    private User user;

}