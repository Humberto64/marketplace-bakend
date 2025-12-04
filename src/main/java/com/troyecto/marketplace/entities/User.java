package com.troyecto.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    // Nota: usar Long para phone puede estar bien, pero en muchos casos es mejor String
    // para preservar ceros a la izquierda y símbolos internacionales.
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String role;
    //cascade propaga las operaciones de persistencia de una entidad A a una B
    //Si borramos la entidad A se borraría la entidad B por ejemplo
    // - mappedBy indica que la entidad Order tiene la FK (relación bidireccional).
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();
    // Relación OneToMany con Review:
    // - @JsonBackReference evita serialización recursiva; la otra parte (Review) debería usar @JsonManagedReference.
    // - Atención: OneToMany por defecto es LAZY; acceder a reviews fuera de una transacción puede lanzar LazyInitializationException.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();
    // Relación OneToMany con Store:
    // - misma lógica que las anteriores: mantener consistencia bidireccional usando add/remove.
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Store> stores = new ArrayList<>();
}