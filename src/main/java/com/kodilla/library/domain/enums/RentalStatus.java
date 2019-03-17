package com.kodilla.library.domain.enums;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum RentalStatus {
    AVAILABLE, LOST, RENTED, DAMAGED;
}
