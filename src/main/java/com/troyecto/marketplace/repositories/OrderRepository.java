package com.troyecto.marketplace.repositories;

import com.troyecto.marketplace.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
