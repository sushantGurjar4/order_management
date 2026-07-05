package com.sushant.ordermanagement.customer.application;

import com.sushant.ordermanagement.common.exception.ResourceNotFoundException;
import com.sushant.ordermanagement.customer.domain.Customer;
import com.sushant.ordermanagement.customer.dto.CreateCustomerRequest;
import com.sushant.ordermanagement.customer.dto.CustomerResponse;
import com.sushant.ordermanagement.customer.dto.UpdateCustomerRequest;
import com.sushant.ordermanagement.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        if (customerRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Customer with email already exists: " + request.email());
        }

        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        Customer saved = customerRepository.save(customer);
        return mapToResponse(saved);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToResponse(customer);
    }

    @Override
    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public CustomerResponse updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        customer.setName(request.name());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());

        Customer updated = customerRepository.save(customer);
        return mapToResponse(updated);
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}