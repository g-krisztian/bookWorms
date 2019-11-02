package com.bookworms.library.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookworms.library.dao.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query(value = "SELECT * FROM customers WHERE full_name LIKE %:name%", nativeQuery = true)
    List<CustomerEntity> findByName(String name);

    @Query(value = "SELECT * FROM customers WHERE email=:email", nativeQuery = true)
    List<CustomerEntity> findByEmail(String email);

}
