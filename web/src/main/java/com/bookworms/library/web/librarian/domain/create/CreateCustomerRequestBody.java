package com.bookworms.library.web.librarian.domain.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerRequestBody {

    private String fullName;
    private String email;

}
