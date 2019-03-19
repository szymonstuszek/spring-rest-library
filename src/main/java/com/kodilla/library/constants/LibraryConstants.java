package com.kodilla.library.constants;

import java.math.BigDecimal;

public interface LibraryConstants {
    long DAYS_TO_RETURN_BOOK = 14;
    BigDecimal PENALTY_PER_DAY_OVERDUE = new BigDecimal(1);
    BigDecimal PENALTY_FOR_DAMAGING_A_BOOK = new BigDecimal(5);
    BigDecimal PENALTY_FOR_LOSING_A_BOOK = new BigDecimal(15);
}
