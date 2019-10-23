package com.bookworms.library.service.domain;

import com.bookworms.library.dao.entities.BookStatusEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class BookStatus {
    private Long id;
    private Long overAllCopies;
    private Long availableCopies;
    private List<Customer> subscribers;

    public BookStatus(BookStatusEntity status) {
        this.id = status.getId();
        this.availableCopies = status.getAvailableCopies();
        this.overAllCopies = status.getOverAllCopies();
        this.subscribers = status.getSubscribers().stream().map(Customer::new).collect(Collectors.toList());
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }
}
