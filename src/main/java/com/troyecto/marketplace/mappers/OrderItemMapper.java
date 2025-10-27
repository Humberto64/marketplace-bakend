package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.OrderItem;
import com.troyecto.marketplace.entities.Product;

public class OrderItemMapper {
    public static OrderItemDTO mapOrderItemToOrderItemDTO(OrderItem orderItem){
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setSubtotal(orderItem.getSubtotal());

        Order order = orderItem.getOrder();
        if (order != null){
            dto.setOrderId(order.getId());
        }

        Product product = orderItem.getProduct();
        if (product != null){
            dto.setProductId(product.getId());
            dto.setProductName(product.getName());
        }

        return dto;
    }

    public static OrderItem mapOrderItemDTOtoOrderItem(OrderItemDTO orderItemdto){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemdto.getId());
        orderItem.setQuantity(orderItemdto.getQuantity());
        orderItem.setPrice(orderItemdto.getPrice());
        orderItem.setSubtotal(orderItemdto.getSubtotal());

        return orderItem;
    }
}
