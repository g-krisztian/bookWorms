package com.bookworms.library.dao.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String email;

    private Boolean isActive;

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
            customerEntity.setIsActive(isActive);
            return customerEntity;
        }
    }
}


