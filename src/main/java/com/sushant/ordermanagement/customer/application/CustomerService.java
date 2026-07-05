package com.sushant.ordermanagement.customer.application;

import com.sushant.ordermanagement.customer.dto.CreateCustomerRequest;
import com.sushant.ordermanagement.customer.dto.CustomerResponse;
import com.sushant.ordermanagement.customer.dto.UpdateCustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponse createCustomer(CreateCustomerRequest request);

    CustomerResponse getCustomerById(Long id);

    Page<CustomerResponse> getAllCustomers(Pageable pageable);

    CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request);
}