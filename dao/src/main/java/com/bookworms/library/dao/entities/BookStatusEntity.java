package com.bookworms.library.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "bookstatus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_status_id_seq_gen")
    @SequenceGenerator(name = "book_status_id_seq_gen", sequenceName = "book_status_id_seq", allocationSize = 1)
    private Long id;
    private Long overAllCopies;
    private Long availableCopies;
    @OneToMany
    private List<CustomerEntity> subscribers;

}
