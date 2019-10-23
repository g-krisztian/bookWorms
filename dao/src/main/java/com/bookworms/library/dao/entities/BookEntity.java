
package com.bookworms.library.dao.entities;

import javax.persistence.*;

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
    private String printType;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private BookStatusEntity status;

    public BookEntity(final String author, final String title, final String genre, final String printType, final BookStatusEntity status) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.printType = printType;
        this.status = status;
    }
}