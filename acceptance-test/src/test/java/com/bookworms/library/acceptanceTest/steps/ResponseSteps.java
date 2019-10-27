package com.bookworms.library.acceptanceTest.steps;

import java.util.Map;

import org.junit.Assert;

import com.bookworms.library.acceptanceTest.AbstractSteps;
import com.fasterxml.jackson.core.type.TypeReference;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ResponseSteps extends AbstractSteps {

    private static final TypeReference<Map<String, Object>> RESOURCE_TYPE = new TypeReference<Map<String, Object>>() {};

    @Then("^the responded status code is 200$")
    public void theResponseStatus200() throws Throwable {
        Assert.assertEquals(200, getLastStatusCode());
    }

    @And("^the responded customer is correct$")
    public void theRespondedCustomerIsCorrect() throws Throwable {
        Map<String, Object> response = getLastPostContentAs(RESOURCE_TYPE);
        Map<String, Object> userData = getUserData(response);
        Assert.assertEquals("some test name", userData.get("fullName"));
        Assert.assertEquals("test email", userData.get("email"));
        Assert.assertTrue((Boolean)response.get("isActive"));
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getUserData(Map<String, Object> response) {
        return ((Map<String, Object>) response.get("userData"));
    }
}
