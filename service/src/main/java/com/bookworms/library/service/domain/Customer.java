package com.bookworms.library.service.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookworms.library.dao.entities.CustomerEntity;
import lombok.Getter;

@Getter
public class Customer {
    private final UserData userData;
    private final Boolean isActive;

    public Customer(UserData userData, Boolean isActive) {
        this.userData = userData;
        this.isActive = isActive;
    }

    public Customer(CustomerEntity customerEntity) {
        this.userData= new UserData(customerEntity.getId(),customerEntity.getFullName(),customerEntity.getEmail());
        this.isActive = customerEntity.getIsActive();
    }
}
