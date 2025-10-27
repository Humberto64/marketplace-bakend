package com.troyecto.marketplace.serviceimpls;


import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.OrderItem;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderItemMapper;
import com.troyecto.marketplace.repositories.OrderItemRepository;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.repositories.ProductRepository;
import com.troyecto.marketplace.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {


    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = OrderItemMapper.mapOrderItemDTOtoOrderItem(orderItemDTO);

        //orderItem.setSubtotal(orderItem.getQuantity() * orderItemDTO.getPrice());
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order"));
        orderItem.setOrder(order);

        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product"));
        orderItem.setProduct(product);

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

        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(()-> new ResourceNotFoundException("Order"));
        orderItem.setOrder(order);

        Product product = productRepository.findById(orderItemDTO.getProductId())
                .orElseThrow(()-> new ResourceNotFoundException("Product"));
        orderItem.setProduct(product);

        OrderItem updateOrderItem = orderItemRepository.save(orderItem);

        return OrderItemMapper.mapOrderItemToOrderItemDTO(updateOrderItem);
    }
}
