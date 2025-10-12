package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpls implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.mapOrderDTOtoOrder(orderDTO);
        return OrderMapper.mapOrderToOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar la orden. Orden no econtranda con id: " + id));

        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setItems(orderDTO.getItems());
        order.setSubtotal(orderDTO.getSubtotal());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setTax(orderDTO.getTax());
        order.setPayMethod(orderDTO.getPayMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        Order updateOrder = orderRepository.save(order);

        return OrderMapper.mapOrderToOrderDTO(updateOrder);
    }

    @Override
    public String cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Orden no encontrada con id: " + id));

        orderRepository.delete(order);

        return "Orden con ID " + id + "eliminado exitosamente";
    }

    @Override
    public OrderDTO getOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));

        return OrderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }
}