package com.bookworms.library.service.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class Book
{
    private final Long id;
    private final String author;
    private final String title;
    private final List<Genre> genres;
    private final PrintType printType;

    public Book(Long id, String author, String title, List<Genre> genres, PrintType printType) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genres = genres;
        this.printType = printType;
    }

}
