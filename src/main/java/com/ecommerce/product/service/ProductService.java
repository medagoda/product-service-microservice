package com.ecommerce.product.service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

/**
 * Service-layer contract (Dependency Inversion / SOLID "D") — controllers depend on
 * this interface, not on the concrete implementation.
 */
public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    ProductResponse getProductById(Long productId);

    void deleteProduct(Long productId);
}
