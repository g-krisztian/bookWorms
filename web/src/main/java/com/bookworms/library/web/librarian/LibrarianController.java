package com.bookworms.library.web.librarian;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.*;
import com.bookworms.library.service.librarian.LibrarianService;
import com.bookworms.library.web.customer.domain.BookResponse;
import com.bookworms.library.web.librarian.domain.create.CreateBookRequest;
import com.bookworms.library.web.librarian.domain.create.CreateBookResponse;
import com.bookworms.library.web.customer.domain.create.CreateBorrowRequest;
import com.bookworms.library.web.customer.domain.create.CreateBorrowResponse;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequestBody;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrarianController {

    private final LibrarianService librarianService;
    private final BorrowService borrowService;
    private final BookService bookService;

    public LibrarianController(LibrarianService librarianService, BorrowService borrowService, BookService bookService) {
        this.librarianService = librarianService;
        this.borrowService = borrowService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/librarian/createCustomer")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequestBody createCustomerRequestBody) {
        Customer customer = librarianService.createCustomer(createCustomerRequestBody.getFullName(), createCustomerRequestBody.getEmail());
        return new CreateCustomerResponse(customer.getUserData(), customer.getIsActive());
    }

    @GetMapping(value = "/librarian/getUsers")
    public List<CreateCustomerResponse> getAllCustomers(){
        List<Customer> customers = librarianService.getAllCusttomers();
        return customers.stream().map(c -> new CreateCustomerResponse(c.getUserData(), c.getIsActive())).collect(Collectors.toList());
    }

    @PostMapping(value = "/librarian/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), "active");
        return new CreateBorrowResponse(borrow);
    }

    @PostMapping(value = "/librarian/createBook")
    public CreateBookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        Long copies = createBookRequest.getCopies().orElse(1L);
        Book book = new Book(null,
                createBookRequest.getAuthor(),
                createBookRequest.getTitle(),
                Genre.valueOf(createBookRequest.getGenre()),
                PrintType.valueOf(createBookRequest.getPrintType()),
                new BookStatus(null, copies, copies, Collections.EMPTY_LIST)
        );
        Book savedBook = bookService.createBook(book);
        return new CreateBookResponse(savedBook);
    }

    @GetMapping(value = "/librarian/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping(value = "/librarian/notifyBorrowers")
    public String notifyBorrowers() {
        borrowService.notifyBorrowers();
        return "OK";
    }

    @GetMapping(value = "librarian/getPendingBorrows")
    public List<CreateBorrowResponse> getPendingBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("pending");
        return borrows.stream().map(CreateBorrowResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getActiveBorrows")
    public List<CreateBorrowResponse> getActiveBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("active");
        return borrows.stream().map(CreateBorrowResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getReturningBorrows")
    public List<CreateBorrowResponse> getReturningBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("returning");
        return borrows.stream().map(CreateBorrowResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "librarian/getClosedBorrows")
    public List<CreateBorrowResponse> getClosedBorrows(){
        List<Borrow> borrows = borrowService.getBorrowByState("closed");
        return borrows.stream().map(CreateBorrowResponse::new).collect(Collectors.toList());
    }

}
