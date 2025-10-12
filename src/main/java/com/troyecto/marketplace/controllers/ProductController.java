package com.troyecto.marketplace.controllers;

// Importaciones de tus clases de proyecto (deben coincidir con tus carpetas/paquetes)
import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.mappers.ProductMapper;
import com.troyecto.marketplace.services.ProductService;

// Importaciones estándar de Spring y HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products") // Endpoint base: /api/products
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    /**
     * Endpoint para publicar un nuevo producto en una tienda.
     * Método: POST /api/products
     */
    @PostMapping
    public ResponseEntity<ProductDTO> publishProduct(@RequestBody ProductDTO productDTO) {
        try {
            // 1. Controller usa el Mapper para convertir DTO a Entity
            Product productEntity = productMapper.toEntity(productDTO);

            // 2. Llama al servicio para la lógica de negocio (validación, asignación de fecha)
            Product newProduct = productService.publishNewProduct(productEntity);

            // 3. Mapper convierte la Entity final de vuelta a DTO para la respuesta
            ProductDTO responseDTO = productMapper.toDTO(newProduct);

            // Respuesta de éxito
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Manejo de errores de negocio (ej. producto con nombre duplicado en la misma tienda)
            System.err.println("Error al publicar producto: " + e.getMessage());
            // Uso de ResponseEntity para devolver el mensaje de error y el código 409
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}