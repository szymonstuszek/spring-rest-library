package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Rental {
    private Long id;
    private Long bookCopyId;
    private Long libraryUserId;
    private LocalDate dateOfRental;
    private LocalDate dueOnDate;
}
