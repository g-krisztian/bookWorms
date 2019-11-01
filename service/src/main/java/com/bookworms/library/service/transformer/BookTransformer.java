package com.bookworms.library.service.transformer;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.entities.BookStatusEntity;
import com.bookworms.library.service.domain.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookTransformer {

    private CustomerTransformer customerTransformer;

    public BookTransformer(CustomerTransformer customerTransformer) {
        this.customerTransformer = customerTransformer;
    }

    public Book transform(BookEntity book) {
        return Book.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .genre(Genre.valueOf(book.getGenre()))
                .printType(PrintType.valueOf(book.getPrintType()))
                .status(transform(book.getStatus()))
                .build();
    }

    public BookStatus transform(BookStatusEntity status) {
        return BookStatus.builder()
                .id(status.getId())
                .availableCopies(status.getAvailableCopies())
                .overAllCopies(status.getOverAllCopies())
                .subscribers(status.getSubscribers().stream().map(customerTransformer::transform).collect(Collectors.toList()))
                .build();
    }
}
