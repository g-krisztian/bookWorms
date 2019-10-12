package com.bookworms.library.web.librarian;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequest;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;

@RestController
public class LibrarianController {


    @PostMapping(value = "/librarian/createCustomer")
    public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        return new CreateCustomerResponse();
    }

}
