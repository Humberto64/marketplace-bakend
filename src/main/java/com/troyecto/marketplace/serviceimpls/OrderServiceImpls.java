package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpls implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderDTO createOrder(Long userId, OrderDTO orderDTO) {
        User user = verifyUser(userId);
        Order order = OrderMapper.mapOrderDTOtoOrder(orderDTO);
        return OrderMapper.mapOrderToOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(Long userId, Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar la orden. Orden no econtranda con id: " + orderId));

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
    public String cancelOrder(Long userId, Long orderId) {
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

    private User verifyUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado id: " + id));
    }
}