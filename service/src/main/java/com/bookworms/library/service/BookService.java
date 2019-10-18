package com.bookworms.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookworms.library.dao.entities.BookEntity;
import com.bookworms.library.dao.repositories.BookDao;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(final BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book createBook(final Book book) {
        BookEntity bookEntity = new BookEntity(book.getAuthor(), book.getTitle(), book.getGenre()
                .toString(), book.getPrintType()
                .toString());
        BookEntity savedBook = bookDao.save(bookEntity);
        return new Book(savedBook.getId(), savedBook.getAuthor(), savedBook.getTitle(),
                Genre.valueOf(savedBook.getGenre()), PrintType.valueOf(savedBook.getPrintType()));
    }
}
