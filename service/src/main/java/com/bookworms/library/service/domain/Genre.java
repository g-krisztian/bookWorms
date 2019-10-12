package com.bookworms.library.service.domain;

public enum Genre {
    ACTION("Action"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    SCIFI("Sci-fi");

    private final String value;

    private Genre(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
