package com.nhnacademy;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.function.Try;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ZeroEvenOddTest {

    @Test
    @DisplayName("1<=n<=100")
    void range1To100(){
        Assertions.assertThrows(IllegalArgumentException.class,()->{new ZeroEvenOdd(1000);});
    }

    @Test
    @Disabled
    @DisplayName("init currentNumber = 1")
    void currentNumber_init_currentNumber() throws Exception {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(100);
        Try<Object> t = ReflectionUtils.tryToReadFieldValue(ZeroEvenOdd.class,"currentNumber",zeroEvenOdd);
        Assertions.assertEquals(1,t.get());
    }

    @Test
    @Disabled
    @DisplayName("init zeroPrinted = false")
    void currentNumber_init_zeroPrinted() throws Exception {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(100);
        Try<Object> t = ReflectionUtils.tryToReadFieldValue(ZeroEvenOdd.class,"zeroPrinted",zeroEvenOdd);
        Assertions.assertEquals(false,t.get());
    }


    @ParameterizedTest
    @MethodSource("arguments")
    @DisplayName("1 ~ 5 테스트")
    void zeroEvenOdd(int count, String expected){
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(count);

        StringBuffer buffer = new StringBuffer();
        IntConsumer consumer = n -> {
            buffer.append(n);
        };
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(consumer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(consumer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(consumer);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();

        for(;;){
            if(threadA.getState().equals(Thread.State.TERMINATED) && threadB.getState().equals(Thread.State.TERMINATED) && threadC.getState().equals(Thread.State.TERMINATED)){
                break;
            }
            Thread.yield();
        }

        Assertions.assertEquals(expected, buffer.toString());
    }

    private static Stream<Arguments> arguments(){
        return Stream.of(
                Arguments.of(1,"01"),
                Arguments.of(2,"0102"),
                Arguments.of(3,"010203"),
                Arguments.of(4,"01020304"),
                Arguments.of(5,"0102030405")
        );
    }

}