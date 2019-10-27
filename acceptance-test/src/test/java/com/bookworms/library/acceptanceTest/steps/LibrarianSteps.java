package com.bookworms.library.acceptanceTest.steps;

import com.bookworms.library.acceptanceTest.AbstractSteps;

import cucumber.api.java.en.When;

public class LibrarianSteps extends AbstractSteps {

    private static final String TEST_CUSTOMER = "{\"fullName\": \"some test name\", \"email\": \"test email\"}";

    @When("^the librarian creates a customer$")
    public void theLibrarianCallsCreateCustomer() throws Throwable {
        post("/librarian/createCustomer", TEST_CUSTOMER);
    }

    @When("^the librarian asks for all books$")
    public void theLibrarianCallsBooks() throws Throwable {
        get("/librarian/books");
    }

}
