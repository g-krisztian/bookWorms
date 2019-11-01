package com.bookworms.library.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.bookworms.library.service.transformer.CustomerTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.entities.BookStatusEntity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
import com.bookworms.library.service.transformer.BookTransformer;

public class BookServiceTest {

    private BookRepository bookRepository;

    private BookService underTest;
    private BookTransformer bookTransformer;

    @Before
    public void setup() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookTransformer = new BookTransformer(new CustomerTransformer());

        underTest = new BookService(bookRepository, bookTransformer);
    }

    @Test
    public void getBooks_ShouldCallRepository_ReturnBooks() {
        // GIVEN
        long expectedId = 12;
        Genre expectedGenre = Genre.ACTION;
        PrintType expectedPrintType = PrintType.BOOK;
        long expectedBookStatusId = 34;
        BookStatusEntity bookStatusEntity = new BookStatusEntity(expectedBookStatusId, null, null, Collections.emptyList());
        BookEntity bookEntity = new BookEntity(null, null, expectedGenre.name(), expectedPrintType.name(), bookStatusEntity);
        bookEntity.setId(expectedId);
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(bookEntity));

        // WHEN
        List<Book> result = underTest.getBooks();

        // THEN
        Book actualBook = result.get(0);
        assertEquals(expectedId, actualBook.getId().longValue());
        assertEquals(expectedGenre, actualBook.getGenre());
        assertEquals(expectedPrintType, actualBook.getPrintType());
        assertEquals(expectedBookStatusId, actualBook.getStatus().getId().longValue());
    }

}
