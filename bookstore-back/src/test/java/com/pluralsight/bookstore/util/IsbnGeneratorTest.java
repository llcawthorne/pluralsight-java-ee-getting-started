package com.pluralsight.bookstore.util;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class IsbnGeneratorTest {

    @Test
    public void testGenerateNumber() {
        NumberGenerator sut = new IsbnGenerator();

        String isbn = sut.generateNumber();
        assertTrue(isbn.startsWith("13-5677-"));
        assertTrue(isbn.length() > 8);
    }
}
