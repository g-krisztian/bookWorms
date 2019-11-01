package com.bookworms.library.web.librarian;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.BookStatus;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
import com.bookworms.library.service.domain.UserData;
import com.bookworms.library.service.CustomerService;
import com.bookworms.library.web.controller.LibrarianController;
import com.bookworms.library.web.domain.response.BookResponse;
import com.bookworms.library.web.domain.request.CreateCustomerRequestBody;
import com.bookworms.library.web.domain.response.CustomerResponse;

import com.bookworms.library.web.transformer.BookResponseTransformer;
import com.bookworms.library.web.transformer.CustomerResponseTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibrarianControllerTest {

    private CustomerService customerService;
    private BorrowService borrowService;
    private BookService bookService;

    private LibrarianController underTest;
    private BookResponseTransformer bookResponseTransformer;
    private CustomerResponseTransformer customerResponseTransformer;

    @Before
    public void setUp() {
        customerService = Mockito.mock(CustomerService.class);
        borrowService = Mockito.mock(BorrowService.class);
        bookService = Mockito.mock(BookService.class);
        bookResponseTransformer = new BookResponseTransformer(customerResponseTransformer);
        customerResponseTransformer = new CustomerResponseTransformer();
        underTest = new LibrarianController(customerService, borrowService, bookService, bookResponseTransformer, customerResponseTransformer);
    }

    @Test
    public void createCustomer_ShouldCallService_ReturnResult() {
        // GIVEN
        Long expectedId = 42L;
        String expectedFullName = "outPutFullName";
        String expectedEmail = "outputEmail";
        String inputEmail = "inputEmail";
        String inputFullName = "inputFullName";
        CreateCustomerRequestBody input = new CreateCustomerRequestBody(inputFullName, inputEmail);
        when(customerService.createCustomer(inputFullName, inputEmail)).thenReturn(new Customer(new UserData(expectedId, expectedFullName, expectedEmail), true));

        // WHEN
        CustomerResponse result = underTest.createCustomer(input);

        // THEN
        assertTrue(result.getIsActive());
        assertEquals(expectedId, result.getId());
        assertEquals(expectedFullName, result.getFullName());
        assertEquals(expectedEmail, result.getEmail());
    }

    @Test
    public void getBooks_ShouldCallService_ReturnResults() {
        // GIVEN
        long expectedId = 23L;
        PrintType expectedPrintType = PrintType.COMIC_BOOK;
        Genre expectedGenre = Genre.DRAMA;
        long availableCopies = 2L;
        ArrayList<Customer> expectedSubscribers = new ArrayList<>();
        expectedSubscribers.add(new Customer(null, null));
        BookStatus bookStatus = new BookStatus(null, null, availableCopies, expectedSubscribers);
        Book book = new Book(expectedId, null, null, expectedGenre, expectedPrintType, bookStatus);
        when(bookService.getBooks()).thenReturn(Collections.singletonList(book));

        // WHEN
        List<BookResponse> result = underTest.getBooks();

        // THEN
        BookResponse actualBook = result.get(0);
        assertEquals(expectedId, actualBook.getId().longValue());
        assertEquals(expectedGenre.getValue(), actualBook.getGenres());
        assertEquals(expectedPrintType.getValue(), actualBook.getPrintType());
        assertTrue(actualBook.getAvailable());
    }

}
