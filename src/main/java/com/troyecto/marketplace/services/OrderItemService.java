package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);
    OrderItemDTO getOrderItemById(Long id);
    List<OrderItemDTO> getAllOrderItems();
    String deleteOrderItem(Long id);
    OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO);
}
