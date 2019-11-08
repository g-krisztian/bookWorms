package com.bookworms.library.web.transformer;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.web.domain.response.CustomerResponse;
import com.bookworms.library.web.domain.response.DetailedCustomerResponse;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public DetailedCustomerResponse detailedTransform(Customer customer) {
        return DetailedCustomerResponse.builder()
                .id(customer.getUserData().getId())
                .fullName(customer.getUserData().getFullName())
                .email(customer.getUserData().getEmail())
                .isActive(customer.getActive())
                .build();
    }
}
