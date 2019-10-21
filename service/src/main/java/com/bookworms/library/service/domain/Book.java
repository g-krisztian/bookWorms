package com.bookworms.library.service.domain;

import com.bookworms.library.dao.entities.BookEntity;

import java.util.List;
import java.util.stream.Collectors;

public class Book {
import lombok.Getter;

@Getter
public class Book
{
    private final Long id;
    private final String author;
    private final String title;
    private final Genre genres;
    private final PrintType printType;

    public Book(Long id, String author, String title, Genre genres, PrintType printType) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genres = genres;
        this.printType = printType;
    }

    public Book(BookEntity book) {
        this.id = book.getId();
        this.author = book.getAuthor();
        this.title = book.getTitle();

        this.genres = Genre.valueOf(book.getGenre());
        this.printType = PrintType.valueOf(book.getPrintType());
    }

}
