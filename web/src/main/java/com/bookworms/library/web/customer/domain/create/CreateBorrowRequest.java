package com.bookworms.library.web.customer.domain.create;

import com.bookworms.library.service.domain.Book;
import com.bookworms.library.service.domain.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBorrowRequest {
    private Long bookId;
    private Long customerId;
}
