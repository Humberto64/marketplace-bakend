package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.OrderItem;
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
        verifyUser(orderDTO.getUserId()); // Verifica que el usuario exista antes de asociarlo.

        order.setOrderDate(LocalDateTime.now()); // Fecha actual para la orden.

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        order.setUser(user); // Asociar la entidad User a la Order antes de guardar.
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapOrderToOrderDTO(savedOrder);
    }
    // Comentario:
    // - Se mapea DTO->entidad, se asocia el user y se guarda. Importante: mapear los OrderItems antes de setUser para mantener la consistencia.

    @Override
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede actualizar la orden. Orden no econtranda con id: " + orderId));

        order.setTax(orderDTO.getTax());
        order.setPayMethod(orderDTO.getPayMethod());
        order.setPaymentStatus(orderDTO.getPaymentStatus());

        if (orderDTO.getOrderItems() != null) {
            for (var oI : orderDTO.getOrderItems()) {
                OrderItem newItem = OrderItemMapper.mapOrderItemDTOtoOrderItem(oI);

                boolean exists = order.getOrderItems().stream()
                        .anyMatch(existingItem -> existingItem.getId() != null &&
                                existingItem.getId().equals(newItem.getId()));

                if (!exists) {
                    order.addOrderItem(newItem);
                }
            }
        }
        int totalProducts = order.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum();
        double subtotal = order.getOrderItems().stream().mapToDouble(OrderItem::getSubtotal).sum();

        order.setTotalAmount(totalProducts);
        order.setSubtotal(subtotal);

        Order updateOrder = orderRepository.save(order);
        return OrderMapper.mapOrderToOrderDTO(updateOrder);
    }
    // Comentario:
    // - Al reemplazar la colección de items, es importante mantener la gestión de la relación para que JPA pueda sincronizar correctamente.

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
    // - @Transactional(readOnly = true) ayuda a optimizar las lecturas y evita LazyInitializationException si se accede a colecciones correctamente dentro del contexto transaccional.

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
    // Comentario:
    // - Método auxiliar simple para lanzar excepción si el usuario no existe.
}