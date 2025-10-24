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
                    .filter(Objects::nonNull)
                    .map(OrderItemMapper::mapOrderItemDTOtoOrderItem)
                    .forEach(order::addOrderItem);
        }
        return order;
    }
}
