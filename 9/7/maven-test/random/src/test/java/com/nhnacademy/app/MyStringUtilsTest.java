package com.nhnacademy.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

class MyStringUtilsTest {
    
    @Test
    @DisplayName(value = "isEmpty1-test")
    void isEmpty1() {
        String a = "a";
        assertTrue(MyStringUnits.isEmpty1(a) == MyStringUnits.isEmpty2(a));
    }
}
