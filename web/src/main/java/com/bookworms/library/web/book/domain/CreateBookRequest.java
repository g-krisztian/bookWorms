package com.bookworms.library.web.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateBookRequest {

    private final String author;
    private final String title;
    private final String genre;
    private final String printType;

}
