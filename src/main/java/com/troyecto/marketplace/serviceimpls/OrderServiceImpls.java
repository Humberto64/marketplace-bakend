package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.mappers.OrderMapper;
import com.troyecto.marketplace.repositories.OrderRepository;
import com.troyecto.marketplace.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public String cancelOrder(OrderDTO orderDTO) {
        return "";
    }

    @Override
    public OrderDTO getOrderById(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }
}