package com.bookworms.library.web.librarian.domain.create;

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
