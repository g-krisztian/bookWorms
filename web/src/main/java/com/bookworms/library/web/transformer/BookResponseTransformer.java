package com.bookworms.library.web.transformer;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.web.domain.response.BookResponse;
import com.bookworms.library.web.domain.response.DetailedBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookResponseTransformer {
    private CustomerResponseTransformer customerResponseTransformer;
    @Autowired

    public BookResponseTransformer(CustomerResponseTransformer customerResponseTransformer) {
        this.customerResponseTransformer = customerResponseTransformer;
    }

    public DetailedBookResponse detailedTransformer(Book book) {
        return DetailedBookResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .overAllCopies(book.getStatus().getOverAllCopies())
                .availableCopies(book.getStatus().getAvailableCopies())
                .available(book.getStatus().isAvailable())
                .genres(book.getGenre().getValue())
                .printType(book.getPrintType().getValue())
                .subscribers(book.getStatus().getSubscribers().stream().map(customerResponseTransformer::transform).collect(Collectors.toList()))
                .build();
    }

    public BookResponse minimalTransformer(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .genres(book.getGenre().getValue())
                .printType(book.getPrintType().getValue())
                .available(book.getStatus().isAvailable())
                .build();
    }
}
