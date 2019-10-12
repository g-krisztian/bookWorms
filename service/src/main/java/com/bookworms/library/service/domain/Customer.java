package com.bookworms.library.service.domain;

import java.util.List;

public class Customer {

    private final UserData userData;
    private final List<Borrow> borrows;
    private final List<Book> subscriptions;
    private final Boolean isActive;

    public Customer(UserData userData, List<Borrow> borrows, List<Book> subscriptions, Boolean isActive) {
        this.userData = userData;
        this.borrows = borrows;
        this.subscriptions = subscriptions;
        this.isActive = isActive;
    }

    public UserData getUserData() {
        return userData;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public List<Book> getSubscriptions() {
        return subscriptions;
    }

    public Boolean getActive() {
        return isActive;
    }
}
