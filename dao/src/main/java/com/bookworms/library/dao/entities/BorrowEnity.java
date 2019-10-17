package com.bookworms.library.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "borrows")
@Getter
@Setter
public class BorrowEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  Long customerId;
    private  Long bookId;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  BigDecimal libraryFine;
    private  boolean isActive;
    private String status;

    public BorrowEnity(Long customerId, Long bookId, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive) {
        this(null, customerId, bookId, startDate, endDate, libraryFine, isActive , isActive? "active" : "pending");
    }

    public BorrowEnity(Long id, Long customerId, Long bookId, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive, String status) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.libraryFine = libraryFine;
        this.isActive = isActive;
        this.status = status;
    }
}
