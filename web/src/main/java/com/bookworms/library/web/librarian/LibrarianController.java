package com.bookworms.library.web.librarian;

import com.bookworms.library.service.customer.CustomerService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.librarian.LibrarianService;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequestBody;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrarianController {

    private LibrarianService librarianService;
    private CustomerService customerService;

    @Autowired
    public LibrarianController(LibrarianService librarianService, CustomerService customerService) {
        this.librarianService = librarianService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/librarian/createCustomer")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequestBody createCustomerRequestBody) {
        Customer customer = librarianService.createCustomer(createCustomerRequestBody.getFullName(), createCustomerRequestBody.getEmail());
        return new CreateCustomerResponse(customer.getUserData(), customer.getBorrows(), customer.getSubscriptions(), customer.getIsActive());
    }

    @PostMapping(value = "/librarian/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = customerService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), true);
        return new CreateBorrowResponse(borrow);
    }
}
