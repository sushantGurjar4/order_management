package com.sushant.ordermanagement.product.dto;

import com.sushant.ordermanagement.product.domain.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String sku,
        String name,
        String description,
        BigDecimal price,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}