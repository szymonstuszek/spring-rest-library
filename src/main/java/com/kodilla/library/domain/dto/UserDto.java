package com.kodilla.library.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accountCreationDate;
    private boolean active;

    public UserDto(Long id,
                   String firstName,
                   String lastName,
                   LocalDate accountCreationDate,
                   boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreationDate = accountCreationDate;
        this.active = active;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
