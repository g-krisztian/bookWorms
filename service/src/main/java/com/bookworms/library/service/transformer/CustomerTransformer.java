package com.bookworms.library.service.transformer;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;
import org.springframework.stereotype.Component;

@Component
public class CustomerTransformer {
    public Customer transform(CustomerEntity customer) {
        return Customer.builder()
                .userData(new UserData(customer.getId(),customer.getFullName(),customer.getEmail()))
                .active(customer.getIsActive())
                .build();
    }
}
