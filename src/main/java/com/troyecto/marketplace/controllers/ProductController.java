package com.troyecto.marketplace.controllers;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;
import com.troyecto.marketplace.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Le dice a Spring que esta clase es un controlador que manejará peticiones REST.
@RequestMapping("/api/products") // Define la URL base para todos los endpoints en esta clase.
@CrossOrigin("*")
@AllArgsConstructor // Para inyectar el servicio
public class ProductController {
    private ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO){
        ProductDTO savedProduct= productService.createProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> products= productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductsById(@PathVariable Long id){
        ProductDTO productDTO=productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        ProductDTO savedProduct=productService.updateProduct(id, productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String message=productService.cancelProduct(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
