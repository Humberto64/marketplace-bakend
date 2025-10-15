package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.OrderDTO;
import com.troyecto.marketplace.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Le dice a Spring que esta clase es un controlador que manejará peticiones REST.
@RequestMapping("/api/orders") // Define la URL base para todos los endpoints en esta clase.
@CrossOrigin("*")
@AllArgsConstructor // Para inyectar el servicio.
public class OrderController {

    // Inyectamos el servicio que tiene toda la lógica de negocio.
    private final OrderService orderService;

    // Endpoint para CREAR un usuario.
    // Se activa con una petición POST a http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED); // Devuelve el usuario creado y un código 201.
    }

    // Endpoint para OBTENER TODOS los usuarios.
    // Se activa con una petición GET a http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK); // Devuelve la lista y un código 200.
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK); // Devuelve el usuario y un código 200.
    }
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDetails) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDetails);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        String message = orderService.cancelOrder(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
