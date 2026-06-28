package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.exception.ProductNotFoundException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product("Wireless Mouse", new BigDecimal("19.99"), "Ergonomic mouse", "Electronics", 100);
        sampleProduct.setProductId(1L);
    }

    @Test
    void createProduct_savesAndReturnsResponse() {
        ProductRequest request = new ProductRequest();
        request.setName("Wireless Mouse");
        request.setUnitPrice(new BigDecimal("19.99"));
        request.setDescription("Ergonomic mouse");
        request.setCategory("Electronics");
        request.setStock(100);

        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        ProductResponse response = productService.createProduct(request);

        assertNotNull(response);
        assertEquals(1L, response.getProductId());
        assertEquals("Wireless Mouse", response.getName());
        assertEquals(new BigDecimal("19.99"), response.getUnitPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getProductById_whenFound_returnsResponse() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));

        ProductResponse response = productService.getProductById(1L);

        assertEquals("Wireless Mouse", response.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void getProductById_whenNotFound_throwsException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99L));
    }

    @Test
    void deleteProduct_whenExists_deletesSuccessfully() {
        when(productRepository.existsById(1L)).thenReturn(true);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteProduct_whenNotExists_throwsException() {
        when(productRepository.existsById(42L)).thenReturn(false);

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(42L));
        verify(productRepository, never()).deleteById(any());
    }
}