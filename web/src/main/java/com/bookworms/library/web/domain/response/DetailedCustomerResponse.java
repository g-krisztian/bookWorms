package com.bookworms.library.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DetailedCustomerResponse {
    private final Long id;
    private final String fullName;
    private final String email;
    private final Boolean isActive;
    private final List<BookResponse> subscribes;
    private final List<BorrowResponse> borrows;
}
