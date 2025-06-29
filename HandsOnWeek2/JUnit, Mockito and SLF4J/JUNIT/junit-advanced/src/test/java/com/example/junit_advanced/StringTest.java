package com.example.junit_advanced;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {
    @Test
    void testUpperCase() {
        System.out.println("Running StringTest...");
        assertEquals("HELLO", "hello".toUpperCase());
    }
}
