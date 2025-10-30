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

    // Convierte Product (entidad JPA) a ProductDTO.
    // Importante: desreferencia relaciones perezosas (store, orderItems, reviews)
    // y usa mappers específicos para los elementos de las listas.
    public static ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();

        // Mapeo directo de campos simples
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setStock(product.getStock());
        productDTO.setPublishedDate(product.getPublishedDate());
        productDTO.setIsAvailable(product.getIsAvailable());

        // Manejo de la relación ManyToOne -> Store
        // Se comprueba nulo porque la relación puede no estar cargada o ser null.
        Store store = product.getStore();
        if (store != null) {
            productDTO.setStoreId(store.getId());
            productDTO.setStoreName(store.getName());
        }

        // Manejo de OneToMany -> OrderItems
        // Si la colección es null no se hace el mapping; podría preferirse una lista vacía.
        List<OrderItemDTO> orderItemDTO = null;
        if (product.getOrderItems() != null) {
            // Comentario:
            // - Ojo: acceder a product.getOrderItems() cuando la colección es LAZY fuera de una transacción
            //   puede lanzar LazyInitializationException. Asegurarse que la llamada ocurre dentro del contexto transaccional.
            orderItemDTO = product.getOrderItems()
                    .stream()
                    .map(OrderItemMapper::mapOrderItemToOrderItemDTO)
                    .collect(Collectors.toList());
        }
        productDTO.setOrderItem(orderItemDTO);

        // Manejo de OneToMany -> Reviews
        // Igual que arriba: si la colección existe se mapea con ReviewMapper.
        List<ReviewDTO> reviewDTO = null;
        if (product.getReviews() != null) {
            reviewDTO = product.getReviews()
                    .stream()
                    .map(ReviewMapper::mapReviewtoReviewDTO)
                    .collect(Collectors.toList());
        }
        productDTO.setReviews(reviewDTO);

        return productDTO;
    }

    // Convierte ProductDTO a Product (entidad).
    // Importante: usa los métodos addOrderItem/addReview para mantener la relación bidireccional
    // (establece product en cada OrderItem/Review).
    public static Product mapProductDTOtoProduct(ProductDTO productDTO) {
        Product product = new Product();

        // Mapeo directo de campos simples
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setDescription(productDTO.getDescription());

        // publishedDate se copia directamente
        product.setPublishedDate(productDTO.getPublishedDate());

        // NOTA: actualmente no se asigna product.setIsAvailable(...) ni se asocia Store.
        // Si el DTO trae storeId, aquí habría que recuperar la Store (p. ej. desde repositorio)
        // o al menos setear una referencia parcial según la estrategia de diseño.
        // TODO: mapear isAvailable y store si procede.

        // Mapeo de OrderItems: se filtran nulos y se usan los add* para mantener relaciones
        if (productDTO.getOrderItem() != null) {
            productDTO.getOrderItem().stream()
                    .filter(Objects::nonNull)
                    .map(OrderItemMapper::mapOrderItemDTOtoOrderItem)
                    .forEach(product::addOrderItem); // addOrderItem establece product en cada OrderItem
        }

        // Mapeo de Reviews: idem a OrderItems
        if (productDTO.getReviews() != null) {
            productDTO.getReviews().stream()
                    .filter(Objects::nonNull)
                    .map(ReviewMapper::mapReviewDTOtoReview)
                    .forEach(product::addReview); // addReview establece product en cada Review
        }

        return product;
    }
}
