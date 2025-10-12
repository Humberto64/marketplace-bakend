package com.troyecto.marketplace.serviceimpls;


import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.OrderItem;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderItemMapper;
import com.troyecto.marketplace.repositories.OrderItemRepository;
import com.troyecto.marketplace.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        return OrderItemMapper.mapOrderItemToOrderItemDTO(orderItemRepository.save(orderItem));
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item de la Orden no encontrado con id" + id));

        return OrderItemMapper.mapOrderItemToOrderItemDTO(orderItem);
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {

        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream()
                .map(OrderItemMapper::mapOrderItemToOrderItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteOrderItem(Long id) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se pudo eliminar Item de la Orden.Item de la Orden no encontrado con id" + id));

        orderItemRepository.delete(orderItem);

        return "Item de la orden con ID " + id + "eliminado exitosamente";
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {

        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se pudo actualizar. Item de la orden no encontrado con id: " + id));

        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setSubtotal(orderItemDTO.getSubtotal());

        OrderItem updateOrderItem = orderItemRepository.save(orderItem);

        return OrderItemMapper.mapOrderItemToOrderItemDTO(updateOrderItem);
    }
}
