package com.bookworms.library.web.transformer;

import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.web.domain.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerResponseTransformer {
    public CustomerResponse transform(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getUserData().getId())
                .fullName(customer.getUserData().getFullName())
                .email(customer.getUserData().getEmail())
                .isActive(customer.getActive())
                .build();
    }
}
