package com.bookworms.library.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String genre;
    private String printType;

    public BookEntity(final String author, final String title, final String genre, final String printType) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.printType = printType;
    }
}
