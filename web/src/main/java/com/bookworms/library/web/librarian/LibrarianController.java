package com.bookworms.library.web.librarian;

import java.util.List;
import java.util.stream.Collectors;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
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
        return new CreateCustomerResponse(customer.getUserData(), customer.getBorrows(), customer.getSubscriptions(), customer.getIsActive());
    }

    @PostMapping(value = "/librarian/createBorrow")
    public CreateBorrowResponse createBorrow(@RequestBody CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowService.createBorrow(createBorrowRequest.getCustomer(), createBorrowRequest.getBook(), "active");
        return new CreateBorrowResponse(borrow);
    }

    @PostMapping(value = "/librarian/createBook")
    public CreateBookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        Book book = new Book(null, createBookRequest.getAuthor(), createBookRequest.getTitle(),
                Genre.valueOf(createBookRequest.getGenre()), PrintType.valueOf(createBookRequest.getPrintType()));
        Book savedBook = bookService.createBook(book);
        return new CreateBookResponse(savedBook);
    }

    @GetMapping(value = "/librarian/books")
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping(value="/librarian/notifyBorrowers")
    public String notifyBorrowers(){
        borrowService.notifyBorrowers();
        return "OK";
    }
    //librarybookworms21
    //1!br4ryb00kw0rm8
}
