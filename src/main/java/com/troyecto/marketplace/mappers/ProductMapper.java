package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;
import org.springframework.stereotype.Component;

@Component // Inyectable por Spring
public class ProductMapper {

    /**
     * Convierte el DTO (entrada de la red) a la Entity (para guardar en DB).
     */
    public Product toEntity(ProductDTO dto) {
        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        entity.setStoreId(dto.getStoreId()); // Mapeo de la clave foránea temporal

        // Los campos de control (publishedDate, isAvailable) se asignan en el Service.
        return entity;
    }

    /**
     * Convierte la Entity (respuesta de DB) a un DTO (respuesta de red).
     */
    public ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        dto.setStoreId(entity.getStoreId());

        return dto;
    }
}