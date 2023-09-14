package com.nhnacademy.aiot.basic;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class MainTest {

    private static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of( new int[]{25, 100}, 25 ),
                Arguments.of( new int[]{12, 18}, 6 ),
                Arguments.of( new int[]{66, 132}, 66 ),
                Arguments.of( new int[]{8, 19}, 1 ),
                Arguments.of( new int[]{7, 17}, 1 ),
                Arguments.of( new int[]{144, 12}, 12 ),
                Arguments.of( new int[]{555, 153}, 3 ),
                Arguments.of( new int[]{987, 535}, 1 )
        );
    }

    @MethodSource("cases")
    @ParameterizedTest
    @DisplayName("최대 공약수 구하기")
    public void gcd(int[] input, int output) {
        System.out.printf("Input : %d, %d \t -> Expected : %d %n", input[0], input[1], output);


        int gcd = Main.solution(input[0],input[1]);
        System.out.printf("Your Answer : %s %n", gcd);

        assertThat(gcd, equalTo(output));
    }
}