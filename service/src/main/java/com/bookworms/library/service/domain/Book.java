package com.bookworms.library.service.domain;

import lombok.Getter;

@Getter
public class Book
{
    private final Long id;
    private final String author;
    private final String title;
    private final Genre genre;
    private final PrintType printType;

    public Book(Long id, String author, String title, Genre genre, PrintType printType) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.printType = printType;
    }
}
