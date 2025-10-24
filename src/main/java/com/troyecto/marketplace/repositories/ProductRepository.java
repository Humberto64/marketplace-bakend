package com.troyecto.marketplace.repositories;

import com.troyecto.marketplace.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
