package com.troyecto.marketplace.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data // Incluye Getters, Setters, toString, equals y hashCode (gracias a Lombok)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores") // Mapea a una tabla llamada 'stores' en la base de datos
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la tienda (debe ser único para evitar duplicados)
    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Categoría de la tienda (ej. Electrónica, Ropa, Libros)
    @Column(nullable = false)
    private String category;

    // El ID del usuario propietario, que se usará para la relación ManyToOne después
    private Long ownerId;

    // Fecha de creación de la tienda
    @Column(nullable = false)
    private LocalDateTime createdDate;

    // Campo para saber si la tienda está activa o suspendida
    private boolean isActive;

    // **********************************************
    // NOTA: Aquí irán los campos de relación (User, Product) en la siguiente fase.
    // **********************************************
}