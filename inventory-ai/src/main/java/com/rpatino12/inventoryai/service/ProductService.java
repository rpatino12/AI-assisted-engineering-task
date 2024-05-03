package com.rpatino12.inventoryai.service;

import com.rpatino12.inventoryai.exception.ResourceNotFoundException;
import com.rpatino12.inventoryai.model.Product;
import com.rpatino12.inventoryai.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        log.info("Getting product with id={}", id);
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        log.info("Saving product: {}", product);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        log.info("Updating product with id={}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        log.info("Deleting product with id={}", id);
        productRepository.deleteById(id);
    }

    @PostConstruct
    public void init(){
        log.info("Product service started");
    }
}
