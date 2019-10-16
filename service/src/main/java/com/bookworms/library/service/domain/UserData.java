package com.bookworms.library.service.domain;

/**
 * User related data which has no strong relation to the process of borrowing or the library.
 */
public class UserData {

    private final Long id;
    private final String fullName;
    private final String email;

    public UserData(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() { return id; }

}
