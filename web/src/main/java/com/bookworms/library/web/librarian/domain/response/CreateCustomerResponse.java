package com.bookworms.library.web.librarian.domain.response;

import com.bookworms.library.service.domain.UserData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class CreateCustomerResponse {

    private final UserData userData;
    private final Boolean isActive;

}
