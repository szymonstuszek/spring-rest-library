package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookCopy {
    private Long id;
    private Long bookId;

    //enum: inStock, lost, rented
    private String status;
}
