package com.bookworms.library.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bookworms.library.dao.entities.BookStatusEntity;
import com.bookworms.library.service.domain.BookStatus;
import org.springframework.stereotype.Service;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(final Book book) {
        BookStatusEntity status = new BookStatusEntity(
                book.getStatus().getId(),
                book.getStatus().getOverAllCopies(),
                book.getStatus().getAvailableCopies(),
                Collections.EMPTY_LIST
        );
        BookEntity bookEntity = new BookEntity(book.getAuthor(),
                book.getTitle(),
                book.getGenre().toString(),
                book.getPrintType().toString(),
                status);
        BookEntity savedBook = bookRepository.save(bookEntity);
        return new Book(savedBook.getId(),
                savedBook.getAuthor(),
                savedBook.getTitle(),
                Genre.valueOf(savedBook.getGenre()),
                PrintType.valueOf(savedBook.getPrintType()),
                new BookStatus(savedBook.getStatus()));
    }

    public List<Book> getBooks() {
        return bookRepository.findAll().stream().map(Book::new).collect(Collectors.toList());
    }
}
