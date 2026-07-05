package com.sushant.ordermanagement.customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCustomerRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 150, message = "Name must not exceed 150 characters")
        String name,

        @Size(max = 20, message = "Phone must not exceed 20 characters")
        String phone,

        @Size(max = 300, message = "Address must not exceed 300 characters")
        String address
) {
}