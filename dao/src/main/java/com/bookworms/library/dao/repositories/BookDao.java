package com.bookworms.library.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookworms.library.dao.entities.BookEntity;

@Repository
@Transactional
public interface BookDao extends JpaRepository<BookEntity, Long> {

}
