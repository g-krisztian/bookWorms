package com.bookworms.library.web.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class DetailedBookResponse {
    private final Long id;
    private final String author;
    private final String title;
    private final String genres;
    private final String printType;
    private final Boolean available;
    private final Long overAllCopies;
    private final Long availableCopies;
    private final List<CustomerResponse> subscribers;
}
