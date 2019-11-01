package com.bookworms.library.web.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBorrowRequest {
    private Long bookId;
    private Long customerId;
}
