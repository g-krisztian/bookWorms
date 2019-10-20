package com.bookworms.library.service.librarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;

@Component
public class LibrarianService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String fullName, String email) {
        CustomerEntity customerEntity = CustomerEntity.Builder.forCustomer().withFullName(fullName).withEmail(email).withActive(true).build();
        CustomerEntity customerEntitySaved = customerRepository.saveAndFlush(customerEntity);
        return new Customer(new UserData(customerEntitySaved.getId(), customerEntitySaved.getFullName(), customerEntitySaved.getEmail()), customerEntitySaved.getIsActive());
    }

}
