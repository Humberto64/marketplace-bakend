package com.troyecto.marketplace.repositories;

import com.troyecto.marketplace.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Consulta personalizada (derivada): Permite encontrar todos los productos de una tienda específica.
     * Esto será crucial en el futuro para listar el catálogo.
     */
    List<Product> findByStoreId(Long storeId);

    /**
     * Consulta personalizada: Permite verificar si un producto con un nombre específico ya existe en una tienda.
     */
    boolean existsByNameAndStoreId(String name, Long storeId);
}