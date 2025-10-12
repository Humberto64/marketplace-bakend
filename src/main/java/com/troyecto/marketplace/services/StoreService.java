package com.troyecto.marketplace.services;

import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service // Marca esta clase como un Bean de Servicio (Lógica de Negocio)
public class StoreService {

    // 1. INYECCIÓN DE DEPENDENCIAS: El StoreRepository se inyecta.
    private final StoreRepository storeRepository;

    @Autowired // Constructor para inyección de dependencias (forma recomendada)
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Método principal para registrar una nueva tienda.
     * Demuestra el Patrón de Tres Capas: Validación y Persistencia.
     * @param store El objeto Store a guardar.
     * @return La tienda guardada con su ID.
     */
    public Store registerNewStore(Store store) {

        // 2. VALIDACIÓN DE LÓGICA DE NEGOCIO (antes de ir a la DB)
        if (storeRepository.existsByName(store.getName())) {
            // Lanza una excepción clara para que el Controller devuelva un 409 Conflict.
            throw new IllegalArgumentException("El nombre de la tienda '" + store.getName() + "' ya está en uso. Debe ser único.");
        }

        // 3. INYECCIÓN DE DATOS CONTROLADOS POR EL BACKEND
        store.setCreatedDate(LocalDateTime.now());
        store.setActive(true); // Una tienda nueva siempre inicia activa

        // 4. PERSISTENCIA: Guarda la tienda en la base de datos
        return storeRepository.save(store);
    }
}