package com.bookworms.library.service.transformer;

import com.bookworms.library.dao.entities.BorrowEnity;
import com.bookworms.library.service.domain.Borrow;
import org.springframework.stereotype.Component;

@Component
public class BorrowTransformer {

    private final BookTransformer bookTransformer;
    private final CustomerTransformer customerTransformer;

    public BorrowTransformer(BookTransformer bookTransformer, CustomerTransformer customerTransformer) {
        this.bookTransformer = bookTransformer;
        this.customerTransformer = customerTransformer;
    }

    public Borrow transform(BorrowEnity borrow) {
        return Borrow.builder()
                .id(borrow.getId())
                .startDate(borrow.getStartDate())
                .endDate(borrow.getEndDate())
                .libraryFinePerDay(borrow.getLibraryFine())
                .status(borrow.getStatus())
                .book(bookTransformer.transform(borrow.getBook()))
                .customer(customerTransformer.transform(borrow.getCustomer()))
                .build();
    }
}
