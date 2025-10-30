package com.troyecto.marketplace.controllers;


import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.services.OrderItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
@CrossOrigin("*")
@AllArgsConstructor // Para inyectar el servicio.
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        OrderItemDTO savedOrderItem = orderItemService.createOrderItem(orderItemDTO);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    // Endpoint para OBTENER TODOS los usuarios.
    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK); // Devuelve la lista y un código 200.
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        OrderItemDTO orderItem = orderItemService.getOrderItemById(id);
        return new ResponseEntity<>(orderItem, HttpStatus.OK); // Devuelve el usuario y un código 200.
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDetails) {
        OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDetails);
        return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable Long id) {
        String message = orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
