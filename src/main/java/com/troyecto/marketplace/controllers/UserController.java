// Ubicación: src/main/java/com/troyecto/marketplace/controllers/UserController.java
package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Le dice a Spring que esta clase es un controlador que manejará peticiones REST.
@RequestMapping("/api/users") // Define la URL base para todos los endpoints en esta clase.
@CrossOrigin("*")
@AllArgsConstructor // Para inyectar el servicio.
public class UserController {

    // Inyectamos el servicio que tiene toda la lógica de negocio.
    @Autowired
    private UserService userService;

    // Endpoint para CREAR un usuario.
    // Se activa con una petición POST a http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.createUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED); // Devuelve el usuario creado y un código 201.
    }

    // Endpoint para OBTENER TODOS los usuarios.
    // Se activa con una petición GET a http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); // Devuelve la lista y un código 200.
    }

     @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK); // Devuelve el usuario y un código 200.
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails) {
        UserDTO updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String message = userService.deleteUser(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}