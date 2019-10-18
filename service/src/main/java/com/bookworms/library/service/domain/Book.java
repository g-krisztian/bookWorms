package com.bookworms.library.service.domain;

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

    public Book(String author, String title, Genre genre, PrintType printType) {
        this(null, author, title, genre, printType);
    }

    public Long getId() { return id; }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public PrintType getPrintType() {
        return printType;
    }
}
