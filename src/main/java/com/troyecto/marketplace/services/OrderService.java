package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(OrderDTO orderDTO);
    String cancelOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
}
