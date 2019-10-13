package com.bookworms.library.domain;

import java.util.List;

public class Book
{
    private final String author;
    private final String title;
    private final List<Genre> genres;
    private final PrintType printType;

    public Book(String author, String title, List<Genre> genres, PrintType printType) {
        this.author = author;
        this.title = title;
        this.genres = genres;
        this.printType = printType;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public PrintType getPrintType() {
        return printType;
    }
}
