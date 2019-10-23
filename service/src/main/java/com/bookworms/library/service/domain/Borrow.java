package com.bookworms.library.service.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bookworms.library.dao.entities.BorrowEnity;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Relationship between customer and borrowed book. Both startDate and endDate is within the borrowing time range.
 */
@Getter
public class Borrow {

    public static final int MAXIMUM_DAYS_TO_BORROW = 43; //2 weeks from now(+1) on the same day.. And prolonged max twice (+2*14) TODO delete this comment :D

    private final Long id;
    private final Customer customer;
    private final Book book;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final BigDecimal libraryFinePerDay; //aka 'latencyCost' TODO delete this comment
    private String status;

    public Borrow(Customer customer, Book book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, String status) {
        this(null, customer, book, startDate, endDate, libraryFine, status);
    }

    public Borrow(Long id, Customer customer, Book book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, String status) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.libraryFinePerDay = libraryFine;
        this.status = status;
    }

    public Borrow(BorrowEnity entity) {
        this.book = new Book(entity.getBook());
        this.customer = new Customer(entity.getCustomer());
        this.id = entity.getId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.libraryFinePerDay = entity.getLibraryFine();
        this.status = entity.getStatus();
    }

}
