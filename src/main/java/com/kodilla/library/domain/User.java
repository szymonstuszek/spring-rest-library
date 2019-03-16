package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate accountCreationDate;
    private boolean active;
    private double penaltiesAmount = 0;
    private List<Rental> rentals = new ArrayList<>();

    public User(Long id, String firstName, String lastName, LocalDate accountCreationDate, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreationDate = accountCreationDate;
        this.active = true;
    }

    public User(String firstName, String lastName, LocalDate accountCreationDate, boolean active) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountCreationDate = accountCreationDate;
            this.active = active;
    }

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_USER")
    public Long getId() {
        return id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "ACCOUNT_ACTIVE")
    public boolean isActive() {
        return active;
    }

    @Column(name = "ACCOUNT_CREATION_DATE")
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    @OneToMany(
            targetEntity = Rental.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonIgnore
    public List<Rental> getRentals() {
        return rentals;
    }

    @Column(name = "PENALTIES_AMOUNT")
    public double getPenaltiesAmount() {
        return penaltiesAmount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public void setPenaltiesAmount(double penaltiesAmount) {
        this.penaltiesAmount = penaltiesAmount;
    }
}
