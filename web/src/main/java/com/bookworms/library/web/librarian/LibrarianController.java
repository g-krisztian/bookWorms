package com.bookworms.library.web.librarian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookworms.library.domain.Customer;
import com.bookworms.library.domain.UserData;
import com.bookworms.library.service.LibrarianService;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequest;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;

@RestController
public class LibrarianController {

	@Autowired
	private LibrarianService librarian;

	@PostMapping(value = "/librarian/createCustomer")
	public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		Customer customer = librarian
				.createCustomer(new UserData(createCustomerRequest.getName(), createCustomerRequest.getEmail()));
		return new CreateCustomerResponse(customer);
	}

}
