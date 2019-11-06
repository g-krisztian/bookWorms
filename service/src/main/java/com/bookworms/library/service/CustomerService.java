package com.bookworms.library.service;

import com.bookworms.library.service.domain.app.SearchField;
import org.springframework.stereotype.Component;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;
import com.bookworms.library.service.transformer.CustomerTransformer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerTransformer customerTransformer;

    private final Map<SearchField, Function<String, List<CustomerEntity>>> searchMethodMap;

    public CustomerService(CustomerRepository customerRepository, CustomerTransformer customerTransformer) {
        this.customerRepository = customerRepository;
        this.customerTransformer = customerTransformer;
        this.searchMethodMap = new HashMap<SearchField, Function<String, List<CustomerEntity>>>() {{
            put(SearchField.ID, getFindAllByIdFunction());
            put(SearchField.EMAIL, customerRepository::findByEmail);
            put(SearchField.NAME, customerRepository::findByName);
        }};
    }

    public Customer createCustomer(String fullName, String email) {
        CustomerEntity customerEntity = CustomerEntity.Builder.forCustomer().withFullName(fullName).withEmail(email).withActive(true).build();
        CustomerEntity customerEntitySaved = customerRepository.saveAndFlush(customerEntity);
        return new Customer(new UserData(customerEntitySaved.getId(), customerEntitySaved.getFullName(), customerEntitySaved.getEmail()), customerEntitySaved.getIsActive());
    }

    public List<Customer> getAllCustomers() {
        return convertCustomerEntities(customerRepository.findAll());
    }

    public List<Customer> findCustomer(SearchField searchInField, String value) {
        Function<String, List<CustomerEntity>> searchMethod = searchMethodMap.get(searchInField);
        return convertCustomerEntities(searchMethod.apply(value));
    }

    private List<Customer> convertCustomerEntities(List<CustomerEntity> foundCustomerEntities) {
        return foundCustomerEntities.stream().map(customerTransformer::transform).collect(Collectors.toList());
    }

    private Function<String, List<CustomerEntity>> getFindAllByIdFunction() {
        return (value) -> customerRepository.findAllById(Collections.singletonList(Long.valueOf(value)));
    }

    public Customer getCustomer(Long id) {
        return customerTransformer.transform(customerRepository.getOne(id));
    }
}
