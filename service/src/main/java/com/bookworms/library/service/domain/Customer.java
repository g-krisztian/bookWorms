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

    public List<Borrow> addBorrow(Borrow borrow){
        borrows.add(borrow);
        return getBorrows();
    }

    public List<Borrow> removeBorrow(Borrow borrow){
        borrows.remove(borrow);
        return getBorrows();
    }

    public List<Borrow> getBorrows() {
        return Collections.unmodifiableList(borrows);
    }

    public List<Book> addSubscription(Book book){
       subscriptions.add(book);
       return getSubscriptions();
    }

    public List<Book> removeSubscription(Book book){
        subscriptions.remove(book); // TODO Maybe these methods should work with id in the future? // TODO remove comment
        return getSubscriptions();
    }

    public List<Book> getSubscriptions() {
        return Collections.unmodifiableList(subscriptions);
    }

    public Boolean getActive() {
        return isActive;
    }
}
