package com.troyecto.marketplace.dtos.store;

import com.troyecto.marketplace.entities.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private Boolean isActive;

    private Long userId;
    private List<Long> productIds;
}
