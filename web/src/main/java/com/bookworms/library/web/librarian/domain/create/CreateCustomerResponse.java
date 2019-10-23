package com.bookworms.library.web.librarian.domain.create;

import com.bookworms.library.service.domain.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerResponse {

    private final UserData userData;
    private final Boolean isActive;

    public CreateCustomerResponse(UserData userData, Boolean isActive) {
        this.userData = userData;
        this.isActive = isActive;
    }
}
