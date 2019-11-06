package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    @Query(value = "SELECT be FROM BookEntity WHERE c CustomerEntity IN be.status.subscribers")
    List<BookEntity> findAllBookSubscribedByUserId();
}
