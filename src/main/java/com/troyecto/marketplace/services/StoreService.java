package com.troyecto.marketplace.services;


import com.troyecto.marketplace.dtos.StoreDTO;

import java.util.List;

public interface StoreService {
    StoreDTO RegisterNewStore(StoreDTO storeDTO);
    StoreDTO UpdateStore(Long id, StoreDTO storeDTO);
    void DeleteStore(Long id);
    List<StoreDTO> getStores();
}
