package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component // Para que Spring pueda inyectarlo
public class StoreMapper {

    /**
     * Convierte el DTO (recibido por red) en la Entity (para guardar en DB).
     */
    public static Store toEntity(StoreDTO dto) {
        Store entity = new Store();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory(dto.getCategory());;
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setIsActive(dto.getIsActive());
        if(dto.getProducts() != null) {
            dto.getProducts().stream()
                    .filter(Objects::nonNull)
                    .map(ProductMapper::mapProductDTOtoProduct)
                    .forEach(entity::addProduct);
        }

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
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setIsActive(entity.getIsActive());
        User user=entity.getUser();
        if(user!=null){
            dto.setUserId(user.getId());
            dto.setUserName(user.getFirstName()+" "+user.getLastName());
        }
        List<ProductDTO> productDTO=null;
        if(entity.getProducts() != null) {
            productDTO=entity.getProducts()
                    .stream()
                    .map(ProductMapper::mapProductToProductDTO)
                    .collect(Collectors.toList());
        }
        dto.setProducts(productDTO);
        return dto;
    }
}