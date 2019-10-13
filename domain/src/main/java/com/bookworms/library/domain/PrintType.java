package com.bookworms.library.domain;

public enum PrintType {
    BOOK("Book"),
    COMIC_BOOK("Comic book"),
    MAGAZINE("Magazine");

    private final String value;

    PrintType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
