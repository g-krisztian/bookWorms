package com.bookworms.library.service;

import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.dao.repositories.BorrowDao;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CustomerService {

    private final BorrowDao borrowDao;
    private final LibraryService libraryService;

    public CustomerService(BorrowDao borrowDao, LibraryService libraryService) {
        this.borrowDao = borrowDao;
        this.libraryService = libraryService;
    }

    @Transactional
    public Borrow createBorrow(Customer customer, Book book, boolean active) {
        BorrowEnity borrowEnity = new BorrowEnity(customer.getUserData().getId(),
                book.getId(),
                LocalDate.now(),
                LocalDate.now().plusWeeks(2),
                BigDecimal.ZERO,
                active);
        BorrowEnity saved = borrowDao.save(borrowEnity);
        Borrow borrow = new Borrow(saved.getId(), customer, book, saved.getStartDate(), saved.getEndDate(), saved.getLibraryFine(), saved.isActive(), saved.getStatus());
        if (active) {
            libraryService.addActiveBorrow(borrow);
        } else libraryService.addPendingBorrow(borrow);
        return borrow;
    }

}
