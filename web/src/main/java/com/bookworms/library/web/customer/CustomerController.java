package com.bookworms.library.web.customer;

import com.bookworms.library.service.customer.CustomerService;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.customer.domain.BookResponse;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/customer/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = customerService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), false);
        return new CreateBorrowResponse(borrow);
    }

    @GetMapping(value = "/customer/books")
    public List<BookResponse> getBooks() {
        return customerService.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
        //  return new ArrayList<>();
    }

}
