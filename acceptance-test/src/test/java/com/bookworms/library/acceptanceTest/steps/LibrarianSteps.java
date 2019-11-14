package com.bookworms.library.acceptanceTest.steps;

import java.util.Collections;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.bookworms.library.acceptanceTest.AbstractSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class LibrarianSteps extends AbstractSteps {


    @When("^the librarian creates customers with emails:$")
    public void theLibrarianCallsCreateCustomer(List<String> emails) throws Throwable {
        for (String email : emails) {
            post("/librarian/createCustomer", "{\"fullName\": \"some test name\", \"email\": \"" + email + "\"}");
        }
    }

    @When("^the librarian search by emails:$")
    public void theLibrarianCallsFindCustomer(List<String> emails) throws Throwable {
        for (String email : emails) {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("field", "EMAIL");
            params.add("value", email);
            get("/librarian/findCustomer", params);
        }
    }

        @When("^the librarian asks for all books$")
    public void theLibrarianCallsBooks() throws Throwable {
        get("/librarian/books", new LinkedMultiValueMap<>());
    }

}
