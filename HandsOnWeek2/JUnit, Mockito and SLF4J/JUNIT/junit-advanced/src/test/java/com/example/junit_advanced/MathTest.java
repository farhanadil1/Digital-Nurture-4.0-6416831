package com.example.junit_advanced;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @Test
    void testSum() {
        System.out.println("Running MathTest...");
        assertEquals(5, 2 + 3);
    }
}
