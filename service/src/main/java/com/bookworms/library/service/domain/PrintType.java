package com.bookworms.library.service.domain;

import lombok.Getter;

@Getter
public enum PrintType {
    BOOK("Book"),
    COMIC_BOOK("Comic book"),
    MAGAZINE("Magazine");

    private final String value;

    PrintType(String value) {
        this.value = value;
    }

}
