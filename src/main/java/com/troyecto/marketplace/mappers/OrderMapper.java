package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.Order;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDTO mapOrderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setCurrency(order.getCurrency());
        orderDTO.setTax(order.getTax());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setPayMethod(order.getPayMethod());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setSubtotal(order.getSubtotal());

        List<OrderItemDTO> orderItemsDTO = null;
        if(order.getOrderItems() != null) {
            orderItemsDTO = order.getOrderItems()
                    .stream()
                    .map(OrderItemMapper::mapOrderItemToOrderItemDTO)
                    .collect(Collectors.toList());
        }

        orderDTO.setOrderItems(orderItemsDTO);


        // No se mapea userId/userName aquí porque la entidad Order tiene relación lazy; en los servicios se asignan explícitamente si se necesita.
        //Evitar acceder a order.getUser().getId() aquí sin comprobar que la relación esté inicializada para prevenir LazyInitializationException.

        return orderDTO;
    }

    public static Order mapOrderDTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setSubtotal(orderDTO.getSubtotal());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setTax(orderDTO.getTax());
        order.setCurrency(orderDTO.getCurrency());
        order.setPayMethod(orderDTO.getPayMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        if (orderDTO.getOrderItems() != null) {
            orderDTO.getOrderItems().stream()
                    .filter(Objects::nonNull) // Comentario: evita NPE si la lista contiene elementos nulos.
                    .map(OrderItemMapper::mapOrderItemDTOtoOrderItem)
                    .forEach(order::addOrderItem); // Añade cada item y mantiene la relación bidireccional via addOrderItem.
        }
        return order;
    }
}
