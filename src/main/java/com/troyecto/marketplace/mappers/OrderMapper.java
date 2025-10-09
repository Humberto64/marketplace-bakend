package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;

public class OrderMapper {
    public static OrderDTO mapOrderToOrderDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getBuyer(),
                order.getSeller(),
                order.getItems(),
                order.getSubtotal(),
                order.getTotalAmount(),
                order.getTax(),
                order.getCurrency(),
                order.getPayMethod(),
                order.getPaymentStatus(),
                order.getOrderDate()
        );
    }

    public static Order mapOrderDTOtoOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setBuyer(orderDTO.getBuyer());
        order.setSeller(orderDTO.getSeller());
        order.setItems(orderDTO.getItems());
        order.setSubtotal(orderDTO.getSubtotal());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setTax(orderDTO.getTax());
        order.setCurrency(orderDTO.getCurrency());
        order.setPayMethod(orderDTO.getPayMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setOrderDate(orderDTO.getOrderDate());
        return order;
    }
}
