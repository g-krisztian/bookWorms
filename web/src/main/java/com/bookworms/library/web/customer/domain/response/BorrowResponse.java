package com.bookworms.library.web.customer.domain.response;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BorrowResponse {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal libraryFinePerDay;
    private final Long id;

    private final Customer customer;
    private final Book book;
    private final String status;

    public BorrowResponse(Borrow borrow) {
        this.id = borrow.getId();
        this.customer = borrow.getCustomer();
        this.book = borrow.getBook();
        this.startDate = borrow.getStartDate();
        this.endDate = borrow.getEndDate();
        this.libraryFinePerDay = borrow.getLibraryFinePerDay();
        this.status = borrow.getStatus();
    }
}
