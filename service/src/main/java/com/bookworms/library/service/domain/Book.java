package com.bookworms.library.service.domain;

import com.bookworms.library.dao.entities.BookEntity;

import lombok.Getter;

@Getter
public class Book
{
    private final Long id;
    private final String author;
    private final String title;
    private final Genre genre;
    private final PrintType printType;
    private final BookStatus status;

    public Book(Long id, String author, String title, Genre genre, PrintType printType, BookStatus status) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.printType = printType;
        this.status = status;
    }

    public Book(BookEntity book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();
        this.genre = Genre.valueOf(book.getGenre());
        this.printType = PrintType.valueOf(book.getPrintType());
        this.status = new BookStatus(book.getStatus());
    }

}
