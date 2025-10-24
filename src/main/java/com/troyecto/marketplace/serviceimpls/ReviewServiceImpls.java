package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.ReviewDTO;
import com.troyecto.marketplace.entities.Review;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.ReviewMapper;
import com.troyecto.marketplace.repositories.ReviewRepository;
import com.troyecto.marketplace.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpls implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review= ReviewMapper.mapReviewDTOtoReview(reviewDTO);
        Review savedReview= reviewRepository.save(review);
        return ReviewMapper.mapReviewtoReviewDTO(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id,ReviewDTO reviewDTO) {
        Review review=reviewRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No se puede actualizar. Review no encontrada con id: " + id));
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setUpdatedAt(reviewDTO.getUpdatedAt());
        review.setCreatedAt(reviewDTO.getCreatedAt());
        Review savedReview= reviewRepository.save(review);
        return ReviewMapper.mapReviewtoReviewDTO(savedReview);
    }

    @Override
    public String deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Usuario no encontrado con id: " + id));


        reviewRepository.delete(review);


        return "Review con ID " + id + " eliminado exitosamente.";
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrada con id: " + id));

        return ReviewMapper.mapReviewtoReviewDTO(review);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(ReviewMapper::mapReviewtoReviewDTO)
                .collect(Collectors.toList());
    }
}
