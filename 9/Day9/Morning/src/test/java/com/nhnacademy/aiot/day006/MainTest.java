package com.nhnacademy.aiot.day006;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MainTest {
    private static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of("abciwkabc", "abc", "2"),
                Arguments.of("aciwcosck", "c", "3"),
                Arguments.of("banana", "na", "2"),
                Arguments.of("alhorithmtest", "th", "1")
        );
    }

    @ParameterizedTest(name = "{2} \"{1}\" strings in \"{0}\" strings")
    @MethodSource("testCases")
    @DisplayName("특정 문자열 개수 찾기 테스트")
    void algorithm_test(String str, String specific, String answer) {
        Assertions.assertEquals(answer, String.valueOf(Main.solution(str, specific)));
    }
}
