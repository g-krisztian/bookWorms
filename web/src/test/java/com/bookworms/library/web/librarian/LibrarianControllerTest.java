package com.bookworms.library.web.librarian;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.BorrowService;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.BookStatus;
import com.bookworms.library.service.domain.Customer;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
import com.bookworms.library.service.domain.UserData;
import com.bookworms.library.service.librarian.LibrarianService;
import com.bookworms.library.web.customer.domain.BookResponse;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerRequestBody;
import com.bookworms.library.web.librarian.domain.create.CreateCustomerResponse;

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
        assertTrue(result.getIsActive());
        assertEquals(expectedId, result.getUserData().getId());
        assertEquals(expectedFullName, result.getUserData().getFullName());
        assertEquals(expectedEmail, result.getUserData().getEmail());
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
        assertEquals(expectedSubscribers, actualBook.getSubscribers());
        assertTrue(actualBook.getAvailable());
    }

    private CreateCustomerRequestBody createCustomeRequestBody(String inputEmail, String inputFullName) {
        CreateCustomerRequestBody input = new CreateCustomerRequestBody();
        input.setEmail(inputEmail);
        input.setFullName(inputFullName);
        return input;
    }
}
