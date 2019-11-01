package com.bookworms.library.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer {
    private final UserData userData;
    private final Boolean active;

    public Customer(UserData userData, Boolean active) {
        this.userData = userData;
        this.active = active;
    }
}
