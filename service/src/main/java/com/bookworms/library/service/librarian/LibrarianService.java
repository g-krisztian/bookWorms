package com.bookworms.library.service.librarian;

import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;

public class LibrarianService {

    public Customer createCustomer(String fullName, String email){
        return new Customer(new UserData(fullName, email), true);
    }
}
