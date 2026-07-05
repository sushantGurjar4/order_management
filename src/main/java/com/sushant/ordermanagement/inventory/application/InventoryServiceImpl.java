package com.sushant.ordermanagement.inventory.application;

import com.sushant.ordermanagement.common.exception.ResourceNotFoundException;
import com.sushant.ordermanagement.inventory.domain.Inventory;
import com.sushant.ordermanagement.inventory.dto.AddStockRequest;
import com.sushant.ordermanagement.inventory.dto.InventoryResponse;
import com.sushant.ordermanagement.inventory.dto.ReduceStockRequest;
import com.sushant.ordermanagement.inventory.infrastructure.InventoryRepository;
import com.sushant.ordermanagement.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public InventoryResponse addStock(Long productId, AddStockRequest request) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseGet(() -> {
                    Inventory newInventory = new Inventory();
                    newInventory.setProductId(productId);
                    newInventory.setAvailableQty(0);
                    newInventory.setReservedQty(0);
                    return newInventory;
                });

        inventory.setAvailableQty(inventory.getAvailableQty() + request.quantity());

        Inventory saved = inventoryRepository.save(inventory);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public InventoryResponse reduceStock(Long productId, ReduceStockRequest request) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for product id: " + productId));

        if (inventory.getAvailableQty() < request.quantity()) {
            throw new IllegalArgumentException(
                    "Insufficient stock. Available: " + inventory.getAvailableQty() + ", Requested: " + request.quantity()
            );
        }

        inventory.setAvailableQty(inventory.getAvailableQty() - request.quantity());

        Inventory saved = inventoryRepository.save(inventory);
        return mapToResponse(saved);
    }

    @Override
    public InventoryResponse getStockByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory not found for product id: " + productId));
        return mapToResponse(inventory);
    }

    private InventoryResponse mapToResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getAvailableQty(),
                inventory.getReservedQty()
        );
    }
}