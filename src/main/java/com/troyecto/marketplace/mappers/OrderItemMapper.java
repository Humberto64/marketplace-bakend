package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.OrderItem;

public class OrderItemMapper {
    public static OrderItemDTO mapOrderItemToOrderItemDTO(OrderItem orderItem){
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getSubtotal()
        );
    }

    public static OrderItem mapOrderItemDTOtoOrderItem(OrderItemDTO orderItemdto){
        return new OrderItem(
                orderItemdto.getId(),
                orderItemdto.getQuantity(),
                orderItemdto.getPrice(),
                orderItemdto.getSubtotal()
        );
    }
}
