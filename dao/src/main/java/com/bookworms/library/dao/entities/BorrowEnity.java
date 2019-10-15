package com.bookworms.library.dao.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "borrows")
@Getter
@Setter
public class BorrowEnity {
    @Id
    private  Long id;
    private  Long customerId;
    private  Long bookId;
    private  LocalDate startDate;
    private  LocalDate endDate;
    private  BigDecimal libraryFine;
    private  boolean isActive;

    public BorrowEnity(Long customerId, Long bookId, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive) {
        this(null, customerId, bookId, startDate, endDate, libraryFine, isActive);
    }

    public BorrowEnity(Long id, Long customerId, Long bookId, LocalDate startDate, LocalDate endDate, BigDecimal libraryFine, boolean isActive) {
        this.id = id;
        this.customerId = customerId;
        this.bookId = bookId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.libraryFine = libraryFine;
        this.isActive = isActive;
    }
}
