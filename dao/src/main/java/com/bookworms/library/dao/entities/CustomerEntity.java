package com.bookworms.library.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public static final class Builder {
        private String fullName;
        private String email;
        private Boolean isActive;

        private Builder() {
        }

        public static Builder forCustomer() {
            return new Builder();
        }

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withActive(Boolean active) {
            this.isActive = active;
            return this;
        }

        public CustomerEntity build() {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setFullName(fullName);
            customerEntity.setEmail(email);
            customerEntity.setActive(isActive);
            return customerEntity;
        }
    }
}