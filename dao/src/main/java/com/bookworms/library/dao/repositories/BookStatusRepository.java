package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BookStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStatusRepository extends JpaRepository<BookStatusEntity,Long> {

}
