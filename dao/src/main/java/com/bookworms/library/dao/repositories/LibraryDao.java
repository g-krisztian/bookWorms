package com.bookworms.library.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LibraryDao extends JpaRepository {

    @Query(value = "INSERT INTO pending_borrows (borrowId) values (:id)")
    void addPendingBorrow(@Param("id") Long pendingBorrowId);

    @Query(value = "INSERT INTO active_borrows (borrowId) values (:id)")
    void addActiveBorrow(@Param("id") Long id);
}
