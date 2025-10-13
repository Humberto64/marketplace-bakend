package com.troyecto.marketplace.services;


import com.troyecto.marketplace.dtos.StoreDTO;
import org.springframework.stereotype.Service;

public interface StoreService {
    StoreDTO RegisterNewStore(StoreDTO storeDTO);
    StoreDTO UpdateStore(Long id, StoreDTO storeDTO);
}
