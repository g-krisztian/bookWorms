package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BorrowEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface BorrowRepository extends JpaRepository<BorrowEnity, Long> {
    List<BorrowEnity> findAllByStatus(String status);

    List<BorrowEnity> findAllByCustomerId(Long id);
}
