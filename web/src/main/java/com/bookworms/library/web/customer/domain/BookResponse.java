package com.bookworms.library.web.customer.domain;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookResponse {

    private final Long id;
    private String author;
    private String title;
    private final String genres;
    private final String printType;
    private Boolean available;
    private List<Customer> subscribers;

    public BookResponse(Book book){
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.genres = book.getGenre().getValue();
        this.printType = book.getPrintType().getValue();
        this.available = book.getStatus().isAvailable();
        this.subscribers = book.getStatus().getSubscribers();
    }

}
