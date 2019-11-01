package com.bookworms.library.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
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

}
