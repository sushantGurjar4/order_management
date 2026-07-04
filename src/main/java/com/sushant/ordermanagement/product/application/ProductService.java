package com.sushant.ordermanagement.product.application;

import com.sushant.ordermanagement.product.dto.CreateProductRequest;
import com.sushant.ordermanagement.product.dto.ProductResponse;
import com.sushant.ordermanagement.product.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);

    ProductResponse getProductById(Long id);

    Page<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);
}