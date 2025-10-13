package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.entities.Store;
import org.springframework.stereotype.Component;

@Component // Para que Spring pueda inyectarlo
public class StoreMapper {

    /**
     * Convierte el DTO (recibido por red) en la Entity (para guardar en DB).
     */
    public static Store toEntity(StoreDTO dto) {
        Store entity = new Store();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());
        entity.setOwnerId(dto.getOwnerId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setActive(dto.isActive());

        // NOTA: Los campos creados por el backend (createdDate, isActive) NO se mapean aquí.
        return entity;
    }

    /**
     * Convierte la Entity (datos de DB) en un DTO (para responder por red).
     */
    public static StoreDTO toDTO(Store entity) {
        StoreDTO dto = new StoreDTO();

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory(entity.getCategory());
        dto.setOwnerId(entity.getOwnerId());

        return dto;
    }
}