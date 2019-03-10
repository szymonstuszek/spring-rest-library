package com.kodilla.library;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

public class LocalDateTestSuite {

    @Test
    public void testCreatingDate() {
        //Given

        //When

        //Then


    }

    @Test
    public void testConvertLocalDateToSqlDate() {
        //Given
        LocalDate date = LocalDate.of(2015, 8, 11);
        Date convertedDate = Date.valueOf(date);
        System.out.println(convertedDate);
        System.out.println(convertedDate instanceof Date);

        System.out.println(date);
        //When

        //Then


    }
}
