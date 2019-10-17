package com.bookworms.library.service;

import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.dao.repositories.BorrowDao;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CustomerService {

    private final BorrowDao borrowDao;
    private final CustomerRepository customerDao;
    private final BookRepository bookRepository;
    private final LibraryService libraryService;

    @Autowired
    public CustomerService(BorrowDao borrowDao, CustomerRepository customerDao, BookRepository bookRepository, LibraryService libraryService) {
        this.borrowDao = borrowDao;
        this.customerDao = customerDao;
        this.bookRepository = bookRepository;
        this.libraryService = libraryService;
    }

    @Transactional
    public Borrow createBorrow(Customer customer, Book book, boolean active) {
        BorrowEnity borrowEnity = new BorrowEnity(customerDao.getOne(customer.getUserData().getId()),
                bookRepository.getOne(book.getId()),
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                BigDecimal.ZERO,
                active);
        BorrowEnity saved = borrowDao.save(borrowEnity);
        Borrow borrow = new Borrow(saved.getId(), new Customer(customerDao.getOne(customer.getUserData().getId())), book, saved.getStartDate(), saved.getEndDate(), saved.getLibraryFine(), saved.isActive(), saved.getStatus());
        if (active) {
            libraryService.addActiveBorrow(borrow);
        } else libraryService.addPendingBorrow(borrow);
        return borrow;
    }

}
