package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Le dice a Spring que esta clase es un controlador que manejará peticiones REST.
@RequestMapping("/api") // Define la URL base para todos los endpoints en esta clase.
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<OrderDTO> createOrder(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.createOrder(userId, orderDTO);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED); // Devuelve el usuario creado y un código 201.
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDetails) {
        OrderDTO updatedOrder = orderService.updateOrder(orderId, orderDetails);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<String> deleteOrder(@RequestParam(required = false) Long userId, @PathVariable Long orderId) {
        String message = orderService.cancelOrder(userId, orderId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<List<OrderDTO>> List(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.listByUserId(userId));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDTO>> ListAll() {
        return ResponseEntity.ok(orderService.listAll());
    }
}
