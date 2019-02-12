package com.kodilla.library.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum RentalStatus {
    AVAILABLE, LOST, RENTED, DAMAGED;
}
