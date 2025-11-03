package com.troyecto.marketplace.serviceimpls;

import com.troyecto.marketplace.dtos.ProductDTO;
import com.troyecto.marketplace.entities.Product;

import com.troyecto.marketplace.entities.Store;
import com.troyecto.marketplace.exceptions.ResourceNotFoundException;
import com.troyecto.marketplace.mappers.OrderItemMapper;
import com.troyecto.marketplace.mappers.ProductMapper;
import com.troyecto.marketplace.mappers.ReviewMapper;
import com.troyecto.marketplace.repositories.ProductRepository;
import com.troyecto.marketplace.repositories.StoreRepository;
import com.troyecto.marketplace.repositories.UserRepository;
import com.troyecto.marketplace.services.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.mapProductDTOtoProduct(productDTO);
        product.setPublishedDate(LocalDateTime.now());
        //Set store
        Store store=storeRepository.findById(productDTO.getStoreId()).
                orElseThrow(() -> new ResourceNotFoundException("Store not found"));
        product.setStore(store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapProductToProductDTO(savedProduct);
    }
    // Comentario:
    // - Se asigna publishedDate aquí para reflejar la fecha de creación.
    // - Se recupera la Store desde BD y se asocia a Product; hacerlo aquí en el servicio es correcto
    //   porque el mapper no tiene acceso a repositorios.

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

        // Limpieza de colecciones existentes antes de reemplazarlas
        product.getOrderItems().forEach(oI -> oI.setProduct(null));//forEach recorre cada element
        //de la lista y rompe la referencia bidireccional
        product.getOrderItems().clear();//clear vacía la lista
        product.getReviews().forEach(R -> R.setProduct(null));
        product.getReviews().clear();
        // Comentario:
        // - Romper las referencias bidireccionales y limpiar las listas evita "leaks" y permite
        //   que JPA elimine los registros huérfanos cuando orphanRemoval=true.

        if(productDTO.getOrderItem() != null) {
            productDTO.getOrderItem().forEach(oI ->
                    product.addOrderItem(OrderItemMapper.mapOrderItemDTOtoOrderItem(oI)));
        }
        if(productDTO.getReviews() != null) {
            productDTO.getReviews().forEach(R ->//recorre cada elemento de la lista
                    product.addReview(ReviewMapper.mapReviewDTOtoReview(R)));//Setea cada elemento de la lista tipo Review
        }
        productRepository.save(product);
        return ProductMapper.mapProductToProductDTO(product);
    }
    // Comentario:
    // - El método actualiza campos simples y reemplaza colecciones usando los add* para mantener consistencia.
    // - La anotación @Transactional en la clase asegura que las operaciones de lectura/escritura
    //   ocurran dentro de una transacción, evitando LazyInitializationException al acceder a colecciones.

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
