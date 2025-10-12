package com.troyecto.marketplace.entities;

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

    // Nombre del producto (debe ser único dentro de la tienda, pero no a nivel global, por ahora)
    @Column(nullable = false)
    private String name;

    private String description;

    // Precio del producto. Usamos BigDecimal por precisión monetaria. ¡CRÍTICO!
    @Column(nullable = false)
    private BigDecimal price;

    // Stock disponible (entero, ya que no puedes vender 0.5 unidades)
    private Integer stock;

    // El ID de la tienda, que se usará para la relación ManyToOne después
    private Long storeId;

    // Fecha en la que se publica o actualiza el producto
    @Column(nullable = false)
    private LocalDateTime publishedDate;

    // Estado de disponibilidad (puede ser 'true' si hay stock > 0)
    private boolean isAvailable;

    // **********************************************
    // NOTA: Los campos de relación (@ManyToOne Store, @OneToMany OrderItem) se añadirán después.
    // **********************************************
}