package com.bookworms.library.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bookworms.library.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.app.SearchField;
import com.bookworms.library.service.transformer.CustomerTransformer;

public class CustomerServiceTest {

    // dependencies
    private CustomerRepository customerRepository;

    private ArgumentCaptor<CustomerEntity> customerEntityCaptor;
    private CustomerService underTest;
    private CustomerTransformer customerTransformer;

    @Before
    public void setup() {
        customerEntityCaptor = ArgumentCaptor.forClass(CustomerEntity.class);
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerTransformer = Mockito.mock(CustomerTransformer.class);

        underTest = new CustomerService(customerRepository, customerTransformer);
    }

    @Test
    public void createCustomer_ShouldSaveInput_ReturnNewEntity() {
        // GIVEN
        String inputName = "inputFullName";
        String inputEmail = "inputEmail";
        String expectedName = "savedName";
        String expectedEmail = "savedEmail";
        Long expectedId = 42L;
        CustomerEntity savedCustomerEntity = CustomerEntity.Builder.forCustomer().withFullName(expectedName).withEmail(expectedEmail).withActive(true).build();
        savedCustomerEntity.setId(expectedId);
        when(customerRepository.saveAndFlush(customerEntityCaptor.capture())).thenReturn(savedCustomerEntity);

        // WHEN
        Customer result = underTest.createCustomer(inputName, inputEmail);

        // THEN
        CustomerEntity capturedCustomerEntity = customerEntityCaptor.getValue();
        assertTrue(capturedCustomerEntity.getIsActive());
        assertEquals(inputName, capturedCustomerEntity.getFullName());
        assertEquals(inputEmail, capturedCustomerEntity.getEmail());

        assertTrue(result.getActive());
        assertEquals(expectedName, result.getUserData().getFullName());
        assertEquals(expectedEmail, result.getUserData().getEmail());
        assertEquals(expectedId, result.getUserData().getId());
    }

    @Test
    public void findCustomer_ShouldCallFindByEmail_WhenSearchFieldIsEmail() {
        // GIVEN
        SearchField searchField = SearchField.EMAIL;
        String inputEmail = "inputEmail";

        Customer expectedCustomer = Customer.builder().build();
        CustomerEntity savedCustomerEntity = CustomerEntity.Builder.forCustomer().build();

        when(customerRepository.findByEmail(inputEmail)).thenReturn(Collections.singletonList(savedCustomerEntity));
        when(customerTransformer.transform(savedCustomerEntity)).thenReturn(expectedCustomer);

        // WHEN
        List<Customer> result = underTest.findCustomer(searchField, inputEmail);

        // THEN
        assertEquals(expectedCustomer, result.get(0));
    }

    @Test
    public void findCustomer_ShouldCallFindByName_WhenSearchFieldIsName() {
        // GIVEN
        SearchField searchField = SearchField.NAME;
        String inputName = "savedName";

        Customer expectedCustomer = Customer.builder().build();
        CustomerEntity savedCustomerEntity = CustomerEntity.Builder.forCustomer().build();

        when(customerRepository.findByName(inputName)).thenReturn(Collections.singletonList(savedCustomerEntity));
        when(customerTransformer.transform(savedCustomerEntity)).thenReturn(expectedCustomer);

        // WHEN
        List<Customer> result = underTest.findCustomer(searchField, inputName);

        // THEN
        assertEquals(expectedCustomer, result.get(0));
    }

    @Test
    public void findCustomer_ShouldCallFindAllById_WhenSearchFieldIsId() {
        // GIVEN
        SearchField searchField = SearchField.ID;
        String inputId = "32";

        Customer expectedCustomer = Customer.builder().build();
        CustomerEntity savedCustomerEntity = CustomerEntity.Builder.forCustomer().build();

        when(customerRepository.findAllById(Collections.singletonList(Long.valueOf(inputId)))).thenReturn(Collections.singletonList(savedCustomerEntity));
        when(customerTransformer.transform(savedCustomerEntity)).thenReturn(expectedCustomer);

        // WHEN
        List<Customer> result = underTest.findCustomer(searchField, inputId);

        // THEN
        assertEquals(expectedCustomer, result.get(0));
    }
}
