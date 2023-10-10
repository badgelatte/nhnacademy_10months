package com.nhnacademy.aiot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

public class OneTwoTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @ParameterizedTest
    @MethodSource("oneTwoArguments")
    @DisplayName("12 반복 테스트")
    void oneTwo(int count, String expected) {

        OneTwo oneTwo = new OneTwo(count);
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oneTwo.printOne(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print(1);
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oneTwo.printTwo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print(2);
                        }
                    });
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadA.start();
        threadB.start();

        for (;;) {
            if (threadA.getState().equals(Thread.State.TERMINATED)
                    && threadB.getState().equals(Thread.State.TERMINATED)){
                break;
            }
            Thread.yield();
        }
        Assertions.assertEquals(expected,outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> oneTwoArguments(){
        return Stream.of(
                Arguments.of(1,"12"),
                Arguments.of(2,"1212"),
                Arguments.of(3,"121212"),
                Arguments.of(4,"12121212"),
                Arguments.of(5,"1212121212")
        );
    }
}
