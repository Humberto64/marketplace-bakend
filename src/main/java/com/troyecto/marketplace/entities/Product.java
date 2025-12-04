package com.troyecto.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // Incluye Getters, Setters, equals, hashCode, toString (Lombok)
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
    private Boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="store_id",
            nullable = false,
            foreignKey = @ForeignKey(name="fk_product_store"))
    @JsonBackReference
    private Store store;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

}