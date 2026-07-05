package com.sushant.ordermanagement.inventory.api;

import com.sushant.ordermanagement.common.response.ApiResponse;
import com.sushant.ordermanagement.common.util.ApiResponseBuilder;
import com.sushant.ordermanagement.inventory.application.InventoryService;
import com.sushant.ordermanagement.inventory.dto.AddStockRequest;
import com.sushant.ordermanagement.inventory.dto.InventoryResponse;
import com.sushant.ordermanagement.inventory.dto.ReduceStockRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Tag(name = "Inventory", description = "Inventory management APIs")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/{productId}/add")
    @Operation(summary = "Add stock for a product")
    public ResponseEntity<ApiResponse<InventoryResponse>> addStock(
            @PathVariable Long productId,
            @Valid @RequestBody AddStockRequest request
    ) {
        InventoryResponse response = inventoryService.addStock(productId, request);
        return ResponseEntity.ok(ApiResponseBuilder.success("Stock added successfully", response));
    }

    @PostMapping("/{productId}/reduce")
    @Operation(summary = "Reduce stock for a product")
    public ResponseEntity<ApiResponse<InventoryResponse>> reduceStock(
            @PathVariable Long productId,
            @Valid @RequestBody ReduceStockRequest request
    ) {
        InventoryResponse response = inventoryService.reduceStock(productId, request);
        return ResponseEntity.ok(ApiResponseBuilder.success("Stock reduced successfully", response));
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get stock for a product")
    public ResponseEntity<ApiResponse<InventoryResponse>> getStock(@PathVariable Long productId) {
        InventoryResponse response = inventoryService.getStockByProductId(productId);
        return ResponseEntity.ok(ApiResponseBuilder.success("Stock fetched successfully", response));
    }
}