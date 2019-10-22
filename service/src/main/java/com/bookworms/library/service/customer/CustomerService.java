package com.bookworms.library.service.customer;

import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.dao.repositories.BorrowRepository;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.library.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final BorrowRepository borrowRepository;
    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    @Autowired
    public CustomerService(BorrowRepository borrowRepository, CustomerRepository customerRepository, BookRepository bookRepository, LibraryService libraryService) {
        this.borrowRepository = borrowRepository;
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
        this.libraryService = libraryService;
    }

    @Transactional
    public Borrow createBorrow(Customer customer, Book book, boolean active) {
        BorrowEnity borrowEnity = new BorrowEnity(customerRepository.getOne(customer.getUserData().getId()),
                bookRepository.getOne(book.getId()),
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                BigDecimal.ZERO,
                active);
        BorrowEnity saved = borrowRepository.save(borrowEnity);
        Borrow borrow = new Borrow(saved);
        if (active) {
            libraryService.addActiveBorrow(borrow);
        } else libraryService.addPendingBorrow(borrow);
        return borrow;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll().stream().map(Book::new).collect(Collectors.toList());
    }
}
