package com.bookworms.library.service.librarian;

import org.springframework.stereotype.Component;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;
import com.bookworms.library.service.transformer.CustomerTransformer;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibrarianService {

    private final CustomerRepository customerRepository;
    private CustomerTransformer customerTransformer;

    public LibrarianService(CustomerRepository customerRepository, CustomerTransformer customerTransformer) {
        this.customerRepository = customerRepository;
        this.customerTransformer = customerTransformer;
    }

    public Customer createCustomer(String fullName, String email) {
        CustomerEntity customerEntity = CustomerEntity.Builder.forCustomer().withFullName(fullName).withEmail(email).withActive(true).build();
        CustomerEntity customerEntitySaved = customerRepository.saveAndFlush(customerEntity);
        return new Customer(new UserData(customerEntitySaved.getId(), customerEntitySaved.getFullName(), customerEntitySaved.getEmail()), customerEntitySaved.getIsActive());
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerTransformer::transform).collect(Collectors.toList());
    }
}
