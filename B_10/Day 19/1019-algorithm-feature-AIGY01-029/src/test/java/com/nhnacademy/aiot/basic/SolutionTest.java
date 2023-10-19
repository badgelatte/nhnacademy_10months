package com.nhnacademy.aiot.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

@Slf4j
class SolutionTest {
    public static double arr[];

    @BeforeEach
    void setUp(){
        arr = new double[]{-1, 2, 3.91, 4, 6, 39.55};
    }

    @Test
    @DisplayName("empty array")
    void empty_array() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Solution.averageReturnDouble(new double[]{});
        });
    }

    @Test
    @DisplayName("return double-2f")
    void averageReturnDouble() {
        double actual = Solution.averageReturnDouble(SolutionTest.arr);
        log.info("result:{}",actual);
        Assertions.assertEquals(9.08, actual);
    }

    @Test
    @DisplayName("return String-2f")
    void averageReturnString() {
        String actual = Solution.averageReturnString(arr);
        Assertions.assertEquals("9.08",actual);
        log.info("result:{}",actual);

    }
}