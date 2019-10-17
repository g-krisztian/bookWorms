package com.bookworms.library.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "borrows")
@Getter
@Setter
public class BorrowEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "customerid")
    private  CustomerEntity customer;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "bookid")
    private  BookEntity book;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  BigDecimal libraryFine;
    private  boolean isActive;
    private String status;

    public BorrowEnity(CustomerEntity customer, BookEntity book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive) {
        this(null, customer, book, startDate, endDate, libraryFine, isActive , isActive? "active" : "pending");
    }

    public BorrowEnity(Long id, CustomerEntity customer, BookEntity book, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive, String status) {
        this.id = id;
        this.customer = customer;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.libraryFine = libraryFine;
        this.isActive = isActive;
        this.status = status;
    }
}
