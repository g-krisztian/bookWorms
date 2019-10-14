package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BorrowEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface BorrowDao extends JpaRepository {
    BorrowEnity save(BorrowEnity borrow);
}
