package com.bookworms.library.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String author;
    private String title;
    private String genre;
    private Long statusId;
    private String printType;


    public BookEntity(Long id, String author, String title, String genre, Long statusId, String printType) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.statusId = statusId;
        this.printType = printType;
    }

    public BookEntity(String author, String title, String genre, Long statusId) {
        this(null, author,title,genre,statusId, "Book");
    }

}


