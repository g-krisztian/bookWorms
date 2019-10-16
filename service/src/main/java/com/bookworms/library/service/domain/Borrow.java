package com.bookworms.library.service.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Relationship between customer and borrowed book. Both startDate and endDate is within the borrowing time range.
 */
public class Borrow {

    public static final int MAXIMUM_DAYS_TO_BORROW = 43; //2 weeks from now(+1) on the same day.. And prolonged max twice (+2*14) TODO delete this comment :D

    private final Long id;
    private final Customer customer;
    private final Book book;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final BigDecimal libraryFinePerDay; //aka 'latencyCost' TODO delete this comment
    private Boolean isActive;

    public Borrow(Customer customer, Book book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, Boolean isActive) {
        this(null, customer, book, startDate, endDate, libraryFine, isActive);
    }

    public Borrow(Long id, Customer customer, Book book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, Boolean isActive) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.libraryFinePerDay = libraryFine;
        this.isActive = isActive;
    }

    public Long getId() { return id; }

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BigDecimal getLibraryFinePerDay() {
        return libraryFinePerDay;
    }

    public Boolean isActive() {
        return isActive;
    }

}
