package com.example.junit_advanced;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTest {

    @Test
    @Order(3)
    void testC() {
        System.out.println("Test C");
    }

    @Test
    @Order(1)
    void testA() {
        System.out.println("Test A");
    }

    @Test
    @Order(2)
    void testB() {
        System.out.println("Test B");
    }
}
