package com.bookworms.library.web.customer;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.response.BookResponse;
import com.bookworms.library.web.customer.domain.response.BorrowResponse;
import org.springframework.web.bind.annotation.*;

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
    public BorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomerId(), createBorrowRequest.getBookId(), "pending");
        return new BorrowResponse(borrow);
    }

    @GetMapping(value = "/customer/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping("/customer/subscribe")
    public BookResponse subscribe(@RequestBody CreateBorrowRequest createBorrowRequest){
        return new BookResponse( borrowService.subscribe(createBorrowRequest.getCustomerId(),createBorrowRequest.getBookId()));
    }
    @PutMapping(value = "/customer/closeBorrow/{borrowId}")
    public BorrowResponse closeBorrow(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.modifyBorrow(borrowId, "returning");
        return new BorrowResponse(borrow);
    }

}
