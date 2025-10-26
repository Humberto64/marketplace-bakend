package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;

import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderItemMapper;
import com.troyecto.marketplace.mappers.ProductMapper;
import com.troyecto.marketplace.mappers.ReviewMapper;
import com.troyecto.marketplace.repositories.ProductRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        productDTO.setPublishedDate(LocalDateTime.now());
        Product product = ProductMapper.mapProductDTOtoProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapProductToProductDTO(savedProduct);
    }
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product=productRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("No se puede actualizar. Producto no encontrada con id: " + id));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setId(product.getId());
        product.setPublishedDate(productDTO.getPublishedDate());
        product.setStock(productDTO.getStock());
        product.setIsAvailable(productDTO.getIsAvailable());
        product.getOrderItems().forEach(oI -> oI.setProduct(null));
        product.getOrderItems().clear();
        product.getReviews().forEach(R -> R.setProduct(null));
        product.getReviews().clear();
        if(productDTO.getOrderItem() != null) {
            productDTO.getOrderItem().forEach(oI ->
                    product.addOrderItem(OrderItemMapper.mapOrderItemDTOtoOrderItem(oI)));
        }
        if(productDTO.getReviews() != null) {
            productDTO.getReviews().forEach(R ->
                    product.addReview(ReviewMapper.mapReviewDTOtoReview(R)));
        }
        productRepository.save(product);
        return ProductMapper.mapProductToProductDTO(product);
    }
    @Override
    public String cancelProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se puede eliminar. Producto no encontrado con id: " + id));

        productRepository.delete(product);

        return "Product con ID " + id + " eliminado exitosamente.";
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product=productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
        return ProductMapper.mapProductToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products=productRepository.findAll();
        return products.stream()
                .map(ProductMapper::mapProductToProductDTO)
                .collect(Collectors.toList());
    }

}
