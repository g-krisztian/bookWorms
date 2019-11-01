package com.bookworms.library.service.domain;

import com.bookworms.library.dao.entities.CustomerEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {
    private final UserData userData;
    private final Boolean isActive;

    public Customer(UserData userData, Boolean isActive) {
        this.userData = userData;
        this.isActive = isActive;
    }
}
