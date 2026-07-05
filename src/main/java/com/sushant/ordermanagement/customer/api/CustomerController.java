package com.sushant.ordermanagement.customer.api;

import com.sushant.ordermanagement.common.response.ApiResponse;
import com.sushant.ordermanagement.common.util.ApiResponseBuilder;
import com.sushant.ordermanagement.customer.application.CustomerService;
import com.sushant.ordermanagement.customer.dto.CreateCustomerRequest;
import com.sushant.ordermanagement.customer.dto.CustomerResponse;
import com.sushant.ordermanagement.customer.dto.UpdateCustomerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create a new customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(
            @Valid @RequestBody CreateCustomerRequest request
    ) {
        CustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success("Customer created successfully", response));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by id")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable Long id) {
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer fetched successfully", response));
    }

    @GetMapping
    @Operation(summary = "Get all customers with pagination")
    public ResponseEntity<ApiResponse<Page<CustomerResponse>>> getAllCustomers(Pageable pageable) {
        Page<CustomerResponse> response = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customers fetched successfully", response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer by id")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request
    ) {
        CustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(ApiResponseBuilder.success("Customer updated successfully", response));
    }
}