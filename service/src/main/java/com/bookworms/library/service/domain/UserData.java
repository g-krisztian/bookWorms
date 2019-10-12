package com.bookworms.library.service.domain;

/**
 * User related data which has no strong relation to the process of borrowing or the library.
 */
public class UserData {

    private final String name;
    private final String email;

    public UserData(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
