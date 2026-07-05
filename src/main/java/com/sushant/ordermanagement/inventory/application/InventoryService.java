package com.sushant.ordermanagement.inventory.application;

import com.sushant.ordermanagement.inventory.dto.AddStockRequest;
import com.sushant.ordermanagement.inventory.dto.InventoryResponse;
import com.sushant.ordermanagement.inventory.dto.ReduceStockRequest;

public interface InventoryService {

    InventoryResponse addStock(Long productId, AddStockRequest request);

    InventoryResponse reduceStock(Long productId, ReduceStockRequest request);

    InventoryResponse getStockByProductId(Long productId);
}