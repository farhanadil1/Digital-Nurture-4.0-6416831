package com.example.junit_advanced;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    MathTest.class,
    StringTest.class
})
public class AllTest {
}
