package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.mappers.StoreMapper;
import com.troyecto.marketplace.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    private final StoreMapper storeMapper; // 1. Ahora inyectamos el Mapper

    @Autowired
    public StoreController(StoreService storeService, StoreMapper storeMapper) {
        this.storeService = storeService;
        this.storeMapper = storeMapper;
    }

    /**
     * Endpoint para registrar una nueva tienda, usando DTOs para la comunicación.
     * Método: POST /api/stores
     */
    @PostMapping
    // 2. Recibimos el DTO en lugar de la Entity
    public ResponseEntity<StoreDTO> registerStore(@RequestBody StoreDTO storeDTO) {
        try {
            // 3. El Controller usa el Mapper para traducir el DTO a Entity
            Store storeEntity = storeMapper.toEntity(storeDTO);

            // Llama al servicio (que trabaja con la Entity)
            Store newStore = storeService.registerNewStore(storeEntity);

            // 4. Mapeamos la Entity resultante a un DTO para la respuesta
            StoreDTO responseDTO = storeMapper.toDTO(newStore);

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Manejo de error de unicidad (nombre repetido)
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // El método GET (getAllStores) sigue igual por ahora...
}