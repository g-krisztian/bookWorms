package com.bookworms.library.acceptanceTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.bookworms.library.acceptanceTest.AbstractSteps;
import com.fasterxml.jackson.core.type.TypeReference;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ResponseSteps extends AbstractSteps {

    private static final TypeReference<Map<String, Object>> OBJECT_RESOURCE_TYPE = new TypeReference<Map<String, Object>>() {
    };
    private static final TypeReference<List<Map<String, Object>>> LIST_RESOURCE_TYPE = new TypeReference<List<Map<String, Object>>>() {
    };

    @Then("^the responded status code is 200$")
    public void theResponseStatus200() throws Throwable {
        assertEquals(200, getLastStatusCode());
    }

    @And("^the responded customer is correct$")
    public void theRespondedCustomerIsCorrect() throws Throwable {
        Map<String, Object> response = getLastPostContentAs(OBJECT_RESOURCE_TYPE);
        assertEquals("some test name", response.get("fullName"));
        assertEquals("test email", response.get("email"));
        assertTrue((Boolean) response.get("isActive"));
    }

    @And("^the responded books are correct$")
    public void theRespondedBooksAreCorrect() throws Throwable {
        List<Map<String, Object>> response = getLastGetContentAs(LIST_RESOURCE_TYPE);
        Map<String, Object> actualBook = response.get(0);
        assertEquals(1, actualBook.get("id"));
        assertEquals("Douglas Adams", actualBook.get("author"));
        assertEquals("The Hitchhikers Guide to the Galaxy", actualBook.get("title"));
        assertEquals("Sci-fi", actualBook.get("genres"));
        assertEquals("Book", actualBook.get("printType"));
        assertTrue(Boolean.parseBoolean(actualBook.get("available").toString()));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getUserData(Map<String, Object> response) {
        return ((Map<String, Object>) response.get("userData"));
    }
}
