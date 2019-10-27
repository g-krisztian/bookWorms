package com.bookworms.library.web.librarian.domain.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCustomerRequestBody {

    private String fullName;
    private String email;

}
