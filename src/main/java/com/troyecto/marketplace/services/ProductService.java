package com.troyecto.marketplace.services;

import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Lógica de Negocio: Publica un nuevo producto, asegurando que no haya duplicados
     * en la misma tienda y estableciendo el stock inicial.
     * @param product El objeto Product a guardar.
     * @return El producto guardado.
     */
    public Product publishNewProduct(Product product) {

        // 1. VALIDACIÓN: Prevenir que una tienda publique dos productos con el mismo nombre.
        if (productRepository.existsByNameAndStoreId(product.getName(), product.getStoreId())) {
            throw new IllegalArgumentException("Ya existe un producto con el nombre '" + product.getName() + "' en esta tienda.");
        }

        // 2. LÓGICA DE NEGOCIO Y DATOS CONTROLADOS POR EL BACKEND
        product.setPublishedDate(LocalDateTime.now());

        // El producto está disponible si tiene stock inicial.
        if (product.getStock() == null || product.getStock() <= 0) {
            product.setAvailable(false);
        } else {
            product.setAvailable(true);
        }

        // 3. PERSISTENCIA
        return productRepository.save(product);
    }
}