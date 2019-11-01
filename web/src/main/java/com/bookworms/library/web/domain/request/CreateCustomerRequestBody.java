package com.bookworms.library.web.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCustomerRequestBody {

    private String fullName;
    private String email;

}
