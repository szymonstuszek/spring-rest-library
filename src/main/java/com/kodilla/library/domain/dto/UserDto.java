package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accountCreationDate;

    public UserDto(Long id, String firstName, String lastName, LocalDate accountCreationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreationDate = accountCreationDate;
    }

    public UserDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }
}
