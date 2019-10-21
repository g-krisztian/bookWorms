package com.bookworms.library.service.domain;

import lombok.Getter;

/**
 * User related data which has no strong relation to the process of borrowing or the library.
 */
@Getter
public class UserData {

    private final Long id;
    private final String fullName;
    private final String email;

    public UserData(Long id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

}
