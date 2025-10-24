package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
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
}