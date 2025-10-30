package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.StoreDTO;
import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.StoreMapper;
import com.troyecto.marketplace.repositories.StoreRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.StoreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    @Override
    public StoreDTO RegisterNewStore(StoreDTO storeDTO) {
        if(storeRepository.existsByName(storeDTO.getName())){
            throw new IllegalArgumentException("El nombre de la tienda '" + storeDTO.getName() + "' ya está en uso. Debe ser único.");
        }
        storeDTO.setCreatedDate(LocalDateTime.now());
        storeDTO.setIsActive(true);
        Store store = StoreMapper.toEntity(storeDTO);
        User user=userRepository.findById(storeDTO.getUserId()).
                orElseThrow(()->new ResourceNotFoundException("User not found"));
        store.setUser(user);
        Store savedStore = storeRepository.save(store);
        return StoreMapper.toDTO(savedStore);
    }

    @Override
    public StoreDTO getStoreById(Long id) {
        Store store=storeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Store no encontrada con id " + id));

        return StoreMapper.toDTO(store);
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

    @Override
    public String DeleteStore(Long id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Tienda no encontrado con id: " + id));
        storeRepository.delete(store);
        return "Store con ID "+ id +"eliminada exitosamente";
    }

    @Override
    public List<StoreDTO> getStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(StoreMapper::toDTO)
                .toList();
    }

    // Comentarios importantes sobre el servicio:
    // - La verificación existsByName evita violaciones del constraint único antes de intentar guardar.
    // - Se recupera la entidad User en el servicio y se asocia a Store; esto debe hacerse en el servicio
    //   porque el mapper no tiene acceso a repositorios.
    // - @Transactional en la clase asegura que operaciones compuestas (ej. guardar store y sus productos)
    //   se ejecuten en una transacción; además ayuda a evitar LazyInitializationException cuando se accede
    //   a colecciones dentro del servicio.
    // - Las excepciones ResourceNotFoundException son específicas para NOT FOUND; IllegalArgumentException se usa
    //   para validaciones de entrada (nombre duplicado).
}
