package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.ReviewDTO;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.entities.Review;
import com.troyecto.marketplace.entities.User;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.ReviewMapper;
import com.troyecto.marketplace.mappers.UserMapper;
import com.troyecto.marketplace.repositories.ProductRepository;
import com.troyecto.marketplace.repositories.ReviewRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.ProductService;
import com.troyecto.marketplace.services.ReviewService;
import com.troyecto.marketplace.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpls implements ReviewService {
    @Autowired//Inyeccion de dependencias automatica
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    @Override//Sobre escritura de metodos de la interfaz
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        reviewDTO.setCreatedAt(LocalDateTime.now());
        Review review= ReviewMapper.mapReviewDTOtoReview(reviewDTO);
        // Set product
        // - Aquí se recupera la entidad Product desde su id y se asocia al review
        //   no del mapper
        Product product =productRepository.findById(reviewDTO.getProductId()).
                orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        review.setProduct(product);

        // Set user
        User user=userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        review.setUser(user);

        Review savedReview=reviewRepository.save(review);
        return ReviewMapper.mapReviewtoReviewDTO(savedReview);
    }

    @Override
    public ReviewDTO updateReview(Long id,ReviewDTO reviewDTO) {
        Review review=reviewRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No se puede actualizar. Review no encontrada con id: " + id));

        // - Solo se actualizan campos editables; evitar cambiar associations a menos que la API lo permita explícitamente.
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        review.setUpdatedAt(LocalDateTime.now()); // marcar fecha de modificación
        Review savedReview= reviewRepository.save(review);
        return ReviewMapper.mapReviewtoReviewDTO(savedReview);
    }

    @Override
    public String deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Usuario no encontrado con id: " + id));
        // - Al eliminar la review, JPA manejará las referencias; si hay constraints en BD, se lanzará excepción.
        reviewRepository.delete(review);
        return "Review con ID " + id + " eliminado exitosamente.";
    }

    @Override
    public ReviewDTO getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review no encontrada con id: " + id));

        // - Asegurarse que si se necesita product.name o user.name, las relaciones estén inicializadas (transacción activa).
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
