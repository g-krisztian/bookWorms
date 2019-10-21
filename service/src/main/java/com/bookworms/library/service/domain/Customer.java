package com.bookworms.library.service.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookworms.library.dao.entities.CustomerEntity;
import lombok.Getter;

@Getter
public class Customer {
    private final UserData userData;

    private final List<Borrow> borrows = new ArrayList<>();
    private final List<Book> subscriptions = new ArrayList<>();
    private final Boolean isActive;

    public Customer(UserData userData, Boolean isActive) {
        this.userData = userData;
        this.isActive = isActive;
    }

    public Customer(CustomerEntity customerEntity) {
        this.userData= new UserData(customerEntity.getId(),customerEntity.getFullName(),customerEntity.getEmail());
        this.isActive = customerEntity.getIsActive();
    }


    public void addBorrow(Borrow borrow){
        borrows.add(borrow);
    }

    public void removeBorrow(Borrow borrow){
        borrows.remove(borrow);
    }

    public void addSubscription(Book book){
       subscriptions.add(book);
    }

    public void removeSubscription(Book book){
        subscriptions.remove(book); // TODO Maybe these methods should work with id in the future? // TODO remove comment
    }


}
