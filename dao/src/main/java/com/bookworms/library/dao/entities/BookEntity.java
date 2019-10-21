
package com.bookworms.library.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq_gen")
    @SequenceGenerator(name = "book_id_seq_gen", sequenceName = "book_id_seq", allocationSize = 1)
    private Long id;
    private String author;
    private String title;
    private String genre;
    private Long statusId;
    private String printType;

    public BookEntity(final String author, final String title, final String genre, final String printType) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.printType = printType;
    }
}