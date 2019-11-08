package com.bookworms.library.web.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BorrowResponse {
    private final Long id;
    private final String status;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final CustomerResponse customer;
    private final BookResponse book;
    private final BigDecimal libraryFinePerDay;
}

