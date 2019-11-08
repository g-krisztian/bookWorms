package com.bookworms.library.web.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DetailedCustomerResponse {
    private final Long id;
    private final String fullName;
    private final String email;
    private final Boolean isActive;
    @Setter
    private List<BookResponse> subscribes;
    @Setter
    private List<BorrowResponse> borrows;
}
