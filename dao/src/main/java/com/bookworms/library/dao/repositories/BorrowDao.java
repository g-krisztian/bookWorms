package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BorrowEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface BorrowDao extends JpaRepository<BorrowEnity, Long> {
}
