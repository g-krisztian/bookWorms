package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity,Long> {
    @Query(value = "SELECT b FROM BookEntity b JOIN b.status s JOIN s.subscribers c WHERE c.id=:id")
    List<BookEntity> findAllBookSubscribedByUserId(Long id);

}
