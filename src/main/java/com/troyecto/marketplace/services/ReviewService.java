package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO review);
    ReviewDTO updateReview(Long id,ReviewDTO reviewdetails);
    String deleteReview(Long id);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getAllReviews();
}

