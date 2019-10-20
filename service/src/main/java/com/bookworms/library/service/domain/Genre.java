package com.bookworms.library.service.domain;

import lombok.Getter;

@Getter
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

}
