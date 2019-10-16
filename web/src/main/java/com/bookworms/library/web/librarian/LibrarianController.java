package com.bookworms.library.web.librarian;

import com.bookworms.library.service.CustomerService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequestBody;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;

@RestController
public class LibrarianController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/librarian/createCustomer")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequestBody createCustomerRequestBody){
        return new CreateCustomerResponse();
    }


    @PostMapping(value = "/librarian/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest){
        Borrow borrow = customerService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(),true);
        return new CreateBorrowResponse(borrow);
    }

}
