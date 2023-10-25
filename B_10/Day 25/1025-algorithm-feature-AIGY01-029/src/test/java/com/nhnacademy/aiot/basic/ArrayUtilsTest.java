package com.nhnacademy.aiot.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArrayUtilsTest {

    @Test
    @DisplayName("create instance : utility class")
    void TestConstructor() {
        Assertions.assertThrows(IllegalStateException.class, ArrayUtils::new);
    }

    @Test
    @DisplayName("Integer Array Equals : true")
    void testEqualsTrue() {
        Integer[] arr1 = { 1, 2, 3, 4, 5 };
        Integer[] arr2 = { 1, 2, 3, 4, 5 };

        boolean actual = ArrayUtils.equals(arr1, arr2);

        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Integer Array Equals : false")
    void testEqualsFalse() {
        Integer[] arr1 = { 1, 2, 3, 4, 5 };
        Integer[] arr2 = { 5, 6, 7, 8, 9 };

        boolean actual = ArrayUtils.equals(arr1, arr2);

        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("two arrays mismatch length")
    void testMismatchLength() {
        Integer[] arr1 = { 1, 2, 3, 4, 5 };
        Integer[] arr2 = { 1, 2, 3 };

        boolean actual = ArrayUtils.equals(arr1,arr2);

        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("null check")
    void testNullCheck() {
        String[] arr1 = { "a", "b", "c" };
        String[] arr2 = null;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ArrayUtils.equals(arr1, arr2);
        });
    }

    @Test
    @DisplayName("Object Array Equals : true")
    void testEqualsObjectsTrue() {
        User[] users1 = new User[] {
                new User("marco1", "마르코1"),
                new User("marco2", "마르코2"),
                new User("marco3", "마르코3")
        };

        User[] users2 = new User[] {
                new User("marco1", "마르코1"),
                new User("marco2", "마르코2"),
                new User("marco3", "마르코3")
        };

        boolean actual = ArrayUtils.equals(users1, users2);

        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("Object Array Equals : false")
    void testEqualsObjectsFalse() {
        User[] users1 = new User[] {
                new User("marco1", "마르코1"),
                new User("marco2", "마르코2"),
                new User("marco3", "마르코3")
        };

        User[] users2 = new User[] {
                new User("marco1", "마르코1"),
                new User("marco2", "마르코2"),
                new User("marco5", "마르코5")
        };

        boolean actual = ArrayUtils.equals(users1, users2);

        Assertions.assertFalse(actual);
    }
}