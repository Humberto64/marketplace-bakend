
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
    @Column(nullable = false, unique = true)
    private Long phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
        order.setUser(this);
    }
    public void removeOrder(Order order) {
        orders.remove(order);
        order.setUser(null);
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Review> reviews = new ArrayList<>();
    public void addReview(Review review) {
        reviews.add(review);
        review.setUser(this);
    }
    public void removeReview(Review review) {
        reviews.remove(review);
        review.setUser(null);
    }
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonBackReference
    private List<Store> stores = new ArrayList<>();
    public void addStore(Store store) {
        stores.add(store);
        store.setUser(this);
    }
    public void removeStore(Store store) {
        stores.remove(store);
        store.setUser(null);
    }
}