package com.troyecto.marketplace.services;

import com.troyecto.marketplace.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    String cancelProduct(Long id);
    ProductDTO getProductById(Long id); // Recupera y mapea a DTO.
    List<ProductDTO> getAllProducts(); // Lista todos los productos mapeados a DTO.
}
