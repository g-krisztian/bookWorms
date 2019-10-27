package com.bookworms.library.acceptanceTest.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.bookworms.library.acceptanceTest.AbstractSteps;
import com.fasterxml.jackson.core.type.TypeReference;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ResponseSteps extends AbstractSteps {

    private static final TypeReference<Map<String, Object>> OBJECT_RESOURCE_TYPE = new TypeReference<Map<String, Object>>() {};
    private static final TypeReference<List<Object>> LIST_RESOURCE_TYPE = new TypeReference<List<Object>>() {};


    @Then("^the responded status code is 200$")
    public void theResponseStatus200() throws Throwable {
        Assert.assertEquals(200, getLastStatusCode());
    }

    @And("^the responded customer is correct$")
    public void theRespondedCustomerIsCorrect() throws Throwable {
        Map<String, Object> response = getLastPostContentAs(OBJECT_RESOURCE_TYPE);
        Map<String, Object> userData = getUserData(response);
        Assert.assertEquals("some test name", userData.get("fullName"));
        Assert.assertEquals("test email", userData.get("email"));
        Assert.assertTrue(((List)response.get("borrows")).isEmpty());
        Assert.assertTrue(((List)response.get("subscriptions")).isEmpty());
        Assert.assertTrue((Boolean)response.get("isActive"));
    }

    @And("^the responded books are correct$")
    public void theRespondedBooksAreCorrect() throws Throwable {
        List<Object> response = getLastGetContentAs(LIST_RESOURCE_TYPE);
    }


    @SuppressWarnings("unchecked")
    private Map<String, Object> getUserData(Map<String, Object> response) {
        return ((Map<String, Object>) response.get("userData"));
    }
}
