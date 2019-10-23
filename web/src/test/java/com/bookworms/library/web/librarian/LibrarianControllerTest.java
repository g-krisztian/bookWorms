package com.bookworms.library.web.librarian;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.UserData;
import com.bookworms.library.service.librarian.LibrarianService;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequestBody;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LibrarianControllerTest {

    private LibrarianService librarianService;
    private BorrowService borrowService;
    private BookService bookService;

    private LibrarianController underTest;

    @Before
    public void setUp() {
        librarianService = Mockito.mock(LibrarianService.class);
        borrowService = Mockito.mock(BorrowService.class);
        bookService = Mockito.mock(BookService.class);
        underTest = new LibrarianController(librarianService, borrowService, bookService);
    }

    @Test
    public void createCustomer_ShouldCallService_ReturnResult() {
        // GIVEN
        Long expectedId = 42L;
        String expectedFullName = "outPutFullName";
        String expectedEmail = "outputEmail";
        String inputEmail = "inputEmail";
        String inputFullName = "inputFullName";
        CreateCustomerRequestBody input = createCustomeRequestBody(inputEmail, inputFullName);
        when(librarianService.createCustomer(inputFullName, inputEmail)).thenReturn(new Customer(new UserData(expectedId, expectedFullName, expectedEmail), true));

        // WHEN
        CreateCustomerResponse result = underTest.createCustomer(input);

        // THEN
        assertTrue(result.getBorrows().isEmpty());
        assertTrue(result.getSubscriptions().isEmpty());
        assertTrue(result.getIsActive());
        assertEquals(expectedId, result.getUserData().getId());
        assertEquals(expectedFullName, result.getUserData().getFullName());
        assertEquals(expectedEmail, result.getUserData().getEmail());
    }

    private CreateCustomerRequestBody createCustomeRequestBody(String inputEmail, String inputFullName) {
        CreateCustomerRequestBody input = new CreateCustomerRequestBody();
        input.setEmail(inputEmail);
        input.setFullName(inputFullName);
        return input;
    }
}
