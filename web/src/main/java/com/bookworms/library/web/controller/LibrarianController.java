package com.bookworms.library.web.controller;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.*;
import com.bookworms.library.service.CustomerService;
import com.bookworms.library.service.domain.app.SearchField;
import com.bookworms.library.web.domain.request.CreateBorrowRequest;
import com.bookworms.library.web.domain.response.*;
import com.bookworms.library.web.domain.request.CreateBookRequest;
import com.bookworms.library.web.domain.request.CreateCustomerRequestBody;
import com.bookworms.library.web.exception.LibraryException;
import com.bookworms.library.web.transformer.BookResponseTransformer;
import com.bookworms.library.web.transformer.BorrowResponseTransformer;
import com.bookworms.library.web.transformer.CustomerResponseTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LibrarianController {

    private final CustomerService customerService;
    private final BorrowService borrowService;
    private final BookService bookService;
    private final BookResponseTransformer bookResponseTransformer;
    private final CustomerResponseTransformer customerResponseTransformer;
    private final BorrowResponseTransformer borrowResponseTransformer;

    @Autowired
    public LibrarianController(CustomerService customerService, BorrowService borrowService, BookService bookService, BookResponseTransformer bookResponseTransformer, CustomerResponseTransformer customerResponseTransformer, BorrowResponseTransformer borrowResponseTransformer) {
        this.customerService = customerService;
        this.borrowService = borrowService;
        this.bookService = bookService;
        this.bookResponseTransformer = bookResponseTransformer;
        this.customerResponseTransformer = customerResponseTransformer;
        this.borrowResponseTransformer = borrowResponseTransformer;
    }

    // Customer related

    @PostMapping(value = "/librarian/createCustomer")
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequestBody createCustomerRequestBody) {
        Customer customer = customerService.createCustomer(createCustomerRequestBody.getFullName(), createCustomerRequestBody.getEmail());
        return customerResponseTransformer.transform(customer);
    }

    @GetMapping(value = "/librarian/findCustomer")
    public List<CustomerResponse> createCustomer(@RequestParam("field") String field, @RequestParam("value") String value) {
        SearchField searchInField = convertSearchField(field);
        List<Customer> customers = customerService.findCustomer(searchInField, value);
        return customers.stream().map(customerResponseTransformer::transform).collect(Collectors.toList());
    }

    @GetMapping(value = "/librarian/getCustomers")
    public List<CustomerResponse> getAllCustomers(){
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream().map(customerResponseTransformer::transform).collect(Collectors.toList());
    }

    @GetMapping(value = "/librarian/getCustomer/{id}")
    public DetailedCustomerResponse getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        List<BookResponse> subscriptions = bookService.getUserSubscriptions(id).stream().map(bookResponseTransformer::minimalTransformer).collect(Collectors.toList());
        List<BorrowResponse> borrows = borrowService.getBorrowsByUserId(id).stream().map(borrowResponseTransformer::transform).collect(Collectors.toList());

        DetailedCustomerResponse detailedCustomerResponse = customerResponseTransformer.detailedTransform(customer);
        detailedCustomerResponse.setBorrows(borrows);
        detailedCustomerResponse.setSubscribes(subscriptions);
        return detailedCustomerResponse;
    }

    // Book related

    @PostMapping(value = "/librarian/createBook")
    public DetailedBookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        Long copies = createBookRequest.getCopies().orElse(1L);
        Book book = new Book(null,
                createBookRequest.getAuthor(),
                createBookRequest.getTitle(),
                Genre.valueOf(createBookRequest.getGenre()),
                PrintType.valueOf(createBookRequest.getPrintType()),
                new BookStatus(null, copies, copies, Collections.EMPTY_LIST)
        );
        Book savedBook = bookService.createBook(book);
        return bookResponseTransformer.detailedTransformer(savedBook);
    }

    @GetMapping(value = "/librarian/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(bookResponseTransformer::minimalTransformer).collect(Collectors.toList());
    }

    @GetMapping(value = "/librarian/book/{bookId}")
    public DetailedBookResponse getBook(@PathVariable Long bookId) {
        return bookResponseTransformer.detailedTransformer(bookService.getBook(bookId));
    }

    // Borrow related

    @PostMapping(value = "/librarian/createBorrow")
    public BorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomerId(), createBorrowRequest.getBookId(), "active");
        return borrowResponseTransformer.transform(borrow);
    }

    @PutMapping(value = "/librarian/activateBorrow/{borrowId}")
    public BorrowResponse activateBorrow(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.modifyBorrow(borrowId, "active");
        return borrowResponseTransformer.transform(borrow);
    }

    @PutMapping(value = "/librarian/closeBorrow/{borrowId}")
    public BorrowResponse closeBorrow(@PathVariable Long borrowId) {
        Borrow borrow = borrowService.closeBorrow(borrowId);
        borrowService.notifySubscribers(borrow.getBook());
        return borrowResponseTransformer.transform(borrow);
    }

    @GetMapping(value = "librarian/getPendingBorrows")
    public List<BorrowResponse> getPendingBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("pending");
        return borrows.stream().map(borrowResponseTransformer::transform).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getActiveBorrows")
    public List<BorrowResponse> getActiveBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("active");
        return borrows.stream().map(borrowResponseTransformer::transform).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getReturningBorrows")
    public List<BorrowResponse> getReturningBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("returning");
        return borrows.stream().map(borrowResponseTransformer::transform).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getClosedBorrows")
    public List<BorrowResponse> getClosedBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("closed");
        return borrows.stream().map(borrowResponseTransformer::transform).collect(Collectors.toList());
    }

    // Notifications

    @PostMapping(value = "/librarian/notifyBorrowers")
    public String notifyBorrowers() {
        borrowService.notifyBorrowers();
        return "OK";
    }

    // Private methods

    private SearchField convertSearchField(@RequestParam("field") String field) {
        try {
            return SearchField.valueOf(field);
        }catch (Exception e){
            throw new LibraryException("Field is not a valid value. Example values: 'EMAIL', 'NAME', 'ID'", e);
        }
    }

}
