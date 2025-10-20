package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(Long userId, OrderDTO orderDTO);
    OrderDTO updateOrder(Long userId, Long orderId, OrderDTO orderDTO);
    String cancelOrder(Long userId, Long orderId);
    List<OrderDTO> listByUserId(Long id);
}
