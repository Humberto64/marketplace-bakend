package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.dtos.UserDTO;
import com.troyecto.marketplace.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@CrossOrigin("*")
@AllArgsConstructor
public class StoreController {

    private final  StoreService storeService;


    /**
     * Endpoint para registrar una nueva tienda, usando DTOs para la comunicación.
     * Método: POST /api/stores
     */
    @PostMapping
    // 2. Recibimos el DTO en lugar de la Entity
    public ResponseEntity<StoreDTO> registerStore(@RequestBody StoreDTO storeDTO) {
        StoreDTO savedStoreDTO = storeService.RegisterNewStore(storeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStoreDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        StoreDTO updatedStore = storeService.UpdateStore(id, storeDTO);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores() {
        List<StoreDTO> stores = storeService.getStores();
        return ResponseEntity.ok(stores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StoreDTO> deleteStore(@PathVariable Long id) {
        storeService.DeleteStore(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id) {
        StoreDTO store = storeService.getStoreById(id);
        return new ResponseEntity<>(store, HttpStatus.OK); // Devuelve el usuario y un código 200.
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity
                .badRequest()  // HTTP 400
                .body(ex.getMessage()); // Devuelve el mensaje exacto del error
    }
}