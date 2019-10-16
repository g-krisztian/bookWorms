package com.bookworms.library.dao.repositories;

import com.bookworms.library.dao.entities.BorrowEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface LibraryDao extends JpaRepository<BorrowEnity, Long> {

    @Modifying @Query(value = "INSERT INTO pending_borrows (borrowId) VALUES (:id)", nativeQuery = true)
    void addPendingBorrow(@Param("id") Long pendingBorrowId);

    @Modifying @Query(value = "INSERT INTO active_borrows (borrowId) VALUES (:id)", nativeQuery = true)
    void addActiveBorrow(@Param("id") Long id);
}
