package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO); // Crea una orden desde un DTO (mapea a entidad, valida usuario, guarda).
    OrderDTO updateOrder(Long orderId, OrderDTO orderDTO); // Actualiza una orden existente; maneja items (reemplazo).
    OrderDTO getOrderById(Long orderId); // Recupera una orden por id y la mapea a DTO.
    String cancelOrder(Long orderId); // Elimina una orden (p. ej. cancelación).
    List<OrderDTO> listByUserId(Long id); // Lista órdenes de un usuario.
    List<OrderDTO> listAll(); // Lista todas las órdenes.
}
