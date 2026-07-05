package com.sushant.ordermanagement.inventory.dto;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer availableQty,
        Integer reservedQty
) {
}