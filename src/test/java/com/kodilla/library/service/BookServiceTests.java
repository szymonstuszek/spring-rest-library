package com.kodilla.library.service;

import com.kodilla.library.repository.BookCopyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testCountAllByBookAndRentalStatusAvailable() {
        //Given

        //When

        //Then
    }
}
