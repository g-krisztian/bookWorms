package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
