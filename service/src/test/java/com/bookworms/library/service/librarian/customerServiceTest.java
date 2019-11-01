package com.bookworms.library.service.librarian;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import com.bookworms.library.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.bookworms.library.dao.entities.CustomerEntity;
import com.bookworms.library.dao.repositories.CustomerRepository;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.transformer.CustomerTransformer;

public class customerServiceTest {

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

}
