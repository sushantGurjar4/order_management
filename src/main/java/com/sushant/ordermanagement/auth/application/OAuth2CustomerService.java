package com.sushant.ordermanagement.auth.application;

import com.sushant.ordermanagement.customer.domain.Customer;
import com.sushant.ordermanagement.customer.infrastructure.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2CustomerService {

    private final CustomerRepository customerRepository;

    public Customer syncCustomer(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        return customerRepository.findByEmail(email)
                .orElseGet(() -> {
                    Customer customer = new Customer();
                    customer.setEmail(email);
                    customer.setName(name != null ? name : "Google User");
                    customer.setPhone(null);
                    customer.setAddress(null);
                    return customerRepository.save(customer);
                });
    }
}