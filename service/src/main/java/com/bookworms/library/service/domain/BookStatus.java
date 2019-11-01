package com.bookworms.library.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BookStatus {
    private Long id;
    private Long overAllCopies;
    private Long availableCopies;
    private List<Customer> subscribers = new ArrayList<>();

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void addSubscriber(Customer customer) {
        this.subscribers.add(customer);
    }
}
