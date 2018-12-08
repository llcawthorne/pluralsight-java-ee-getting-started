package com.pluralsight.bookstore.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TextUtilTest {

    @Test
    public void testSanitize() {
        TextUtil textUtil = new TextUtil();

        String noSpaces = "title";
        String sanitized = textUtil.sanitize(noSpaces);
        assertEquals(noSpaces, sanitized);

        String oneSpace = "a title";
        sanitized = textUtil.sanitize(oneSpace);
        assertEquals(oneSpace, sanitized);

        String twoSpaces = "a  title";
        sanitized = textUtil.sanitize(twoSpaces);
        assertEquals("a title", sanitized);

        String manySpaces = "a title  with    a lot  of spaces";
        sanitized = textUtil.sanitize(manySpaces);
        assertEquals("a title with a lot of spaces", sanitized);
    }
}
