package com.bookworms.library.service.librarian;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerDao;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;

public class LibrarianService {

    private final CustomerDao customerDao;

    @Autowired
    public LibrarianService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer createCustomer(String fullName, String email) {
        CustomerEntity customerEntity = CustomerEntity.Builder.forCustomer().withFullName(fullName).withEmail(email).withActive(true).build();
        CustomerEntity customerEntitySaved = customerDao.save(customerEntity);
        return new Customer(new UserData(customerEntitySaved.getFullName(), customerEntitySaved.getEmail()), customerEntitySaved.getActive());
    }
}
