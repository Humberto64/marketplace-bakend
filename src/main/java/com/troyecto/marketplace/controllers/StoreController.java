package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        try {
            StoreDTO savedStore = storeService.RegisterNewStore(storeDTO);

            return new ResponseEntity<>(storeDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Manejo de error de unicidad (nombre repetido)
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        StoreDTO updatedStore = storeService.UpdateStore(id, storeDTO);
        return new ResponseEntity<>(updatedStore, HttpStatus.OK);
    }

    // El método GET (getAllStores) sigue igual por ahora...
}