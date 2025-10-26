package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderItemMapper;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpls implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.mapOrderDTOtoOrder(orderDTO);
        verifyUser(orderDTO.getUserId());

        order.setOrderDate(LocalDateTime.now());

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        order.setUser(user);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapOrderToOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar la orden. Orden no econtranda con id: " + orderId));

        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setSubtotal(orderDTO.getSubtotal());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setTax(orderDTO.getTax());
        order.setPayMethod(orderDTO.getPayMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        order.getOrderItems().forEach(oI -> oI.setOrder(null));
        order.getOrderItems().clear();

        if (orderDTO.getOrderItems() != null) {
            orderDTO.getOrderItems().forEach(oI ->
                    order.addOrderItem(OrderItemMapper.mapOrderItemDTOtoOrderItem(oI)));
        }

        Order updateOrder = orderRepository.save(order);
        return OrderMapper.mapOrderToOrderDTO(updateOrder);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order no encontrada con id:" + orderId));

        return  OrderMapper.mapOrderToOrderDTO(order);
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Orden no encontrada con id: " + orderId));

        orderRepository.delete(order);

        return "Orden con ID " + orderId + "eliminado exitosamente";
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDTO> listByUserId(Long id) {
        verifyUser(id);
        return orderRepository.findByUserId(id)
                .stream()
                .map(OrderMapper::mapOrderToOrderDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> listAll() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(OrderMapper::mapOrderToOrderDTO)
                .collect(Collectors.toList());
    }

    private void verifyUser(Long id){
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado id: " + id));
    }
}