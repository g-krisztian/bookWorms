package com.bookworms.library.web.customer;

import com.bookworms.library.service.customer.CustomerService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = customerService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), false);
        return new CreateBorrowResponse(borrow);
    }
}
