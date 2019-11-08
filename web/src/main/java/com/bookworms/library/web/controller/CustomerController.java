package com.bookworms.library.web.controller;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.domain.request.CreateBorrowRequest;
import com.bookworms.library.web.domain.response.BookResponse;
import com.bookworms.library.web.domain.response.BorrowResponse;
import com.bookworms.library.web.transformer.BookResponseTransformer;
import com.bookworms.library.web.transformer.BorrowResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    private final BorrowService borrowService;
    private final BookService bookService;
    private final BookResponseTransformer bookResponseTransformer;
    private final BorrowResponseTransformer borrowResponseTransformer;

    @Autowired
    public CustomerController(BorrowService borrowService, BookService bookService, BookResponseTransformer bookResponseTransformer, BorrowResponseTransformer borrowResponseTransformer) {
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.bookResponseTransformer = bookResponseTransformer;
        this.borrowResponseTransformer = borrowResponseTransformer;
    }

    @PostMapping(value = "/customer/createBorrow")
    public BorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomerId(), createBorrowRequest.getBookId(), "pending");
        return borrowResponseTransformer.transform(borrow);
    }

    @GetMapping(value = "/customer/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(bookResponseTransformer::minimalTransformer).collect(Collectors.toList());
    }

    @GetMapping(value = "/customer/book/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        return bookResponseTransformer.minimalTransformer(bookService.getBook(bookId));
    }

    @PostMapping("/customer/subscribe")
    public BookResponse subscribe(@RequestBody CreateBorrowRequest createBorrowRequest){
        return bookResponseTransformer.minimalTransformer( borrowService.subscribe(createBorrowRequest.getCustomerId(),createBorrowRequest.getBookId()));
    }
    @PutMapping(value = "/customer/closeBorrow/{borrowId}")
    public BorrowResponse closeBorrow(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.modifyBorrow(borrowId, "returning");
        return borrowResponseTransformer.transform(borrow);
    }

}
