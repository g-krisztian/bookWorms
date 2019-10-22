package com.bookworms.library.web.book.domain;

import com.bookworms.library.service.domain.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateBookResponse {

    private final Book book;

}
