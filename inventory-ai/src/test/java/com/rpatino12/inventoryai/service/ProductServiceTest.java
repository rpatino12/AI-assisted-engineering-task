package com.rpatino12.inventoryai.service;

import com.rpatino12.inventoryai.model.Product;
import com.rpatino12.inventoryai.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsTest() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(
                new Product("Product 1", "Description 1", 1.0, 1),
                new Product("Product 2", "Description 2", 2.0, 2)
        ));

        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    void getProductByIdTest() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(
                new Product("Product 1", "Description 1", 1.0, 1)
        ));

        Optional<Product> product = productService.getProductById(1L);
        assertEquals("Product 1", product.orElse(new Product()).getName());
        assertEquals(1, product.orElse(new Product()).getQuantity());
    }

    @Test
    void saveProductTest() {
        Product product = new Product("Product 1", "Description 1", 1.0, 1);
        when(productRepository.save(product)).thenReturn(product);

        assertEquals(product, productService.saveProduct(product));
    }

    @Test
    void updateProductTest() {
        Product product = new Product("Product 1", "Description 1", 1.0, 1);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(1L, product);
        assertEquals(product, updatedProduct);
    }

    @Test
    void deleteProductTest() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}