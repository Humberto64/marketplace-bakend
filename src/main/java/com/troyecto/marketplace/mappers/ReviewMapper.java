package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.ReviewDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.Review;

public class ReviewMapper {
    public static ReviewDTO mapReviewtoReviewDTO(Review review) {
        return new ReviewDTO(
                review.getId(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt(),
                review.getUpdatedAt()
        );
    }
    public static Review mapReviewDTOtoReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        review.setUpdatedAt(reviewDTO.getUpdatedAt());
        return review;
    }
}
