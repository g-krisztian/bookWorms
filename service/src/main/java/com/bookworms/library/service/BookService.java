package com.bookworms.library.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bookworms.library.dao.entities.BookStatusEntity;
import org.springframework.stereotype.Service;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.repositories.BookRepository;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
import com.bookworms.library.service.transformer.BookTransformer;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookTransformer bookTransformer;

    public BookService(final BookRepository bookRepository, BookTransformer bookTransformer) {
        this.bookRepository = bookRepository;
        this.bookTransformer = bookTransformer;
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
        return  bookTransformer.transform(savedBook);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll().stream().map(bookTransformer::transform).collect(Collectors.toList());
    }

    public Book getBook(Long bookId) {
        return bookTransformer.transform(bookRepository.getOne(bookId));
    }

    public List<Book> getUserSubscriptions(Long id) {
        bookRepository.findAllBookSubscribedByUserId();
        return null;
    }
}
