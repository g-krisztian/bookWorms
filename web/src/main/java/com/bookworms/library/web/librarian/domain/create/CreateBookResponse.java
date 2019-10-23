package com.bookworms.library.web.librarian.domain.create;

import com.bookworms.library.service.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateBookResponse {

    private final Book book;

}
