package com.bookworms.library.web.librarian.domain.create;

import java.util.List;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Borrow;
import com.bookworms.library.service.domain.UserData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCustomerResponse {

    private final UserData userData;
    private final List<Borrow> borrows;
    private final List<Book> subscriptions;
    private final Boolean isActive;

    public CreateCustomerResponse(UserData userData, List<Borrow> borrows, List<Book> subscriptions, Boolean isActive) {
        this.userData = userData;
        this.borrows = borrows;
        this.subscriptions = subscriptions;
        this.isActive = isActive;
    }
}
