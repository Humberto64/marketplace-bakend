package com.troyecto.marketplace.mappers;

import com.troyecto.marketplace.dtos.OrderItemDTO;
import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.dtos.ReviewDTO;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.entities.Store;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setPublishedDate(product.getPublishedDate());
        productDTO.setIsAvailable(product.getIsAvailable());
        Store store=product.getStore();
        if(store!=null){
            productDTO.setStoreId(store.getId());
            productDTO.setStoreName(store.getName());
        }
        List<OrderItemDTO> orderItemDTO = null;
        if (product.getOrderItems() != null) {
            orderItemDTO = product.getOrderItems()
                    .stream()
                    .map(OrderItemMapper::mapOrderItemToOrderItemDTO)
                    .collect(Collectors.toList());
        }
        productDTO.setOrderItem(orderItemDTO);
        List<ReviewDTO> reviewDTO = null;
        if (product.getReviews() != null) {
            reviewDTO=product.getReviews()
                    .stream()
                    .map(ReviewMapper::mapReviewtoReviewDTO)
                    .collect(Collectors.toList());
        }
        productDTO.setReviews(reviewDTO);
        return productDTO;
    }
    public static Product mapProductDTOtoProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());
        //product.setIsAvailable(productDTO.getIsAvailable() != null ? productDTO.getIsAvailable() : true);

        product.setPublishedDate(productDTO.getPublishedDate());

        if (productDTO.getOrderItem() != null) {
            productDTO.getOrderItem().stream()
                    .filter(Objects::nonNull)
                    .map(OrderItemMapper::mapOrderItemDTOtoOrderItem)
                    .forEach(product::addOrderItem);
        }
        if(productDTO.getReviews() != null) {
            productDTO.getReviews().stream()
                    .filter(Objects::nonNull)
                    .map(ReviewMapper::mapReviewDTOtoReview)
                    .forEach(product::addReview);
        }
        return product;
    }
}
