package com.bookworms.library.dao.entities;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers", uniqueConstraints =
    @UniqueConstraint(columnNames ={ "email"}))
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq_gen")
    @SequenceGenerator(name = "customer_id_seq_gen", sequenceName = "customer_id_seq", allocationSize = 1)
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
