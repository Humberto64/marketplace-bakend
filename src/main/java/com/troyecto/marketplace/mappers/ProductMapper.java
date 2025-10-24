package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;

public class ProductMapper {
    public static ProductDTO mapProductToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getPublishedDate(),
                product.isAvailable()
        );
    }
    public static Product mapProductDTOtoProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        product.setAvailable(productDTO.isAvailable());
        product.setPublishedDate(productDTO.getPublishedDate());
        return product;
    }
}
