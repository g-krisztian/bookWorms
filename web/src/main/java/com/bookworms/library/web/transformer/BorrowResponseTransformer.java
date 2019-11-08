package com.bookworms.library.web.transformer;

import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.web.domain.response.BorrowResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowResponseTransformer {
    private final BookResponseTransformer bookTransformer;
    private final CustomerResponseTransformer customerTransformer;

    @Autowired
    public BorrowResponseTransformer(BookResponseTransformer bookTransformer, CustomerResponseTransformer customerTransformer) {
        this.bookTransformer = bookTransformer;
        this.customerTransformer = customerTransformer;
    }

    public BorrowResponse transform(Borrow borrow) {
        return BorrowResponse.builder()
                .id(borrow.getId())
                .startDate(borrow.getStartDate())
                .endDate(borrow.getEndDate())
                .book(bookTransformer.minimalTransformer(borrow.getBook()))
                .customer(customerTransformer.transform(borrow.getCustomer()))
                .libraryFinePerDay(borrow.getLibraryFinePerDay())
                .status(borrow.getStatus())
                .build();
    }
}
