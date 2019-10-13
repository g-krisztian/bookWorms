package com.bookworms.library.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookworms.library.dao.entities.CustomerEntity;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,Long> {
    // TODO Question: org.springframework.data.jpa.repository.JpaRepository VS org.springframework.data.repository.CrudRepository
    // TODO Question: Naming is CustomerDao VS CustomerRepository

    public List<CustomerEntity> findByName(String fullName);
}
