package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);
    OrderDTO getOrderById(Long orderId);
    String cancelOrder(Long orderId);
    List<OrderDTO> listByUserId(Long id);
    List<OrderDTO> listAll();
}
