package com.bookworms.library.web.customer;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.customer.domain.BookResponse;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final BorrowService borrowService;
    private final BookService bookService;

    public CustomerController(BorrowService borrowService, BookService bookService) {
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/customer/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), "pending");
        return new CreateBorrowResponse(borrow);
    }

    @GetMapping(value = "/customer/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

}
