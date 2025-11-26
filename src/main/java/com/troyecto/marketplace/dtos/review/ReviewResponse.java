package com.troyecto.marketplace.dtos.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {
    private Long id;
    private Integer rating;
    private String comment;

    private Long productId;
    private Long userId;
}
