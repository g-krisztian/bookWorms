package com.bookworms.library.service;

import java.util.Collections;

import com.bookworms.library.domain.Customer;
import com.bookworms.library.domain.UserData;

public class LibrarianService {
	public Customer createCustomer(UserData userData) {
		return new Customer(userData, Collections.EMPTY_LIST, Collections.EMPTY_LIST, true);
	}
}
