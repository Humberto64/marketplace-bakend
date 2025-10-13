package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.StoreMapper;
import com.troyecto.marketplace.repositories.StoreRepository;
import com.troyecto.marketplace.services.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {

    @Autowired
    private final StoreRepository storeRepository;

    @Override
    public StoreDTO RegisterNewStore(StoreDTO storeDTO) {
        if(storeRepository.existsByName(storeDTO.getName())){
            throw new IllegalArgumentException("El nombre de la tienda '" + storeDTO.getName() + "' ya está en uso. Debe ser único.");
        }

        storeDTO.setCreatedDate(LocalDateTime.now());
        storeDTO.setActive(true);

        Store store = StoreMapper.toEntity(storeDTO);
        Store savedStore = storeRepository.save(store);
        return StoreMapper.toDTO(savedStore);
    }

    @Override
    public StoreDTO UpdateStore(Long id, StoreDTO storeDTO) {

        Store store = storeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se pudo Actualizar. Tienda no encontrada con id: " + id));

        store.setName(storeDTO.getName());
        store.setDescription(storeDTO.getDescription());
        store.setCategory(storeDTO.getCategory());

        Store updatedStore = storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
}
