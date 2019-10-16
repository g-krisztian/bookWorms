package com.bookworms.library.service.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {

    private final UserData userData;
    private final List<Borrow> borrows;
    private final List<Book> subscriptions;
    private final Boolean isActive;

    public Customer(UserData userData, Boolean isActive) {
        this.userData = userData;
        this.borrows = new ArrayList<>();
        this.subscriptions = new ArrayList<>();
        this.isActive = isActive;
    }

    public UserData getUserData() {
        return userData;
    }

    public void addBorrow(Borrow borrow){
        borrows.add(borrow);
    }

    public void removeBorrow(Borrow borrow){
        borrows.remove(borrow);
    }

    public List<Borrow> getBorrows() {
        return Collections.unmodifiableList(borrows);
    }

    public void addSubscription(Book book){
       subscriptions.add(book);
    }

    public void removeSubscription(Book book){
        subscriptions.remove(book); // TODO Maybe these methods should work with id in the future? // TODO remove comment
    }

    public List<Book> getSubscriptions() {
        return Collections.unmodifiableList(subscriptions);
    }

    public Boolean getActive() {
        return isActive;
    }
}
