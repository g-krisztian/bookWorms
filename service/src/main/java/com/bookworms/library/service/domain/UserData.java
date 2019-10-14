package com.bookworms.library.service.domain;

/**
 * User related data which has no strong relation to the process of borrowing or the library.
 */
public class UserData {

    private final Long id;
    private final String name;
    private final String email;

    public UserData(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() { return id; }

}
