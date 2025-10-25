package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.ReviewDTO;
import com.troyecto.marketplace.entities.Order;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.entities.Review;
import com.troyecto.marketplace.entities.User;

public class ReviewMapper {
    public static ReviewDTO mapReviewtoReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setComment(review.getComment());
        reviewDTO.setRating(review.getRating());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        reviewDTO.setUpdatedAt(review.getUpdatedAt());
        Product product=review.getProduct();
        if(product!=null){
            reviewDTO.setProductId(product.getId());
            reviewDTO.setProductName(product.getName());
        }
        User user=review.getUser();
        if(user!=null){
            reviewDTO.setUserId(user.getId());
            reviewDTO.setUserName(user.getFirstName()+" "+user.getLastName());
        }

        return reviewDTO;
    }
    public static Review mapReviewDTOtoReview(ReviewDTO reviewDTO) {
        if(reviewDTO==null)return null;
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        review.setUpdatedAt(reviewDTO.getUpdatedAt());
        return review;
    }
}
