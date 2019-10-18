package com.bookworms.library.web.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookworms.library.service.BookService;
import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Genre;
import com.bookworms.library.service.domain.PrintType;
import com.bookworms.library.web.book.domain.CreateBookRequest;
import com.bookworms.library.web.book.domain.CreateBookResponse;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/book/createBook")
    public CreateBookResponse createBook(@RequestBody CreateBookRequest createBookRequest) {
        Book book = new Book(createBookRequest.getAuthor(), createBookRequest.getTitle(),
                Genre.valueOf(createBookRequest.getGenre()), PrintType.valueOf(createBookRequest.getPrintType()));
        Book savedBook = bookService.createBook(book);
        return new CreateBookResponse(savedBook);
    }

}
