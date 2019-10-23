package com.bookworms.library.web.librarian.domain.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public class CreateBookRequest {

    private final String author;
    private final String title;
    private final String genre;
    private final String printType;
    private final Optional<Long> copies;

}
