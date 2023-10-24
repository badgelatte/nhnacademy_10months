package com.nhnacademy.aiot.basic;


import org.codehaus.plexus.util.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  @DisplayName("utility class")
  void init(){
    Assertions.assertThrows(IllegalStateException.class,()->new ArrayUtils());
  }

  @Test
  @DisplayName("Contains integer")
  void contains_integer() {
    Integer [] arr = {1,2,3,4,5};
    Assertions.assertAll(
        ()->Assertions.assertTrue(ArrayUtils.contains(arr,1)),
        ()->Assertions.assertFalse(ArrayUtils.contains(arr,11))
    );
  }

  @Test
  @DisplayName("contains String")
  void contains_string(){
    String [] arr = {"1","2","3","4","5"};
    Assertions.assertAll(
        ()->Assertions.assertTrue(ArrayUtils.contains(arr,"1")),
        ()->Assertions.assertFalse(ArrayUtils.contains(arr,1))
    );
  }
  
 @Test
    @DisplayName("contains Object")
    void contains_object() {
        User user1 = new User("marco","마르코");
        User user2 = new User("marco","마르코");
        User user3 = new User("marco","마르코");
        User user4 = new User("marco","마르코");

        User[] users = new User[]{
            user1,user2,user3
        };

        Assertions.assertAll(
                ()->Assertions.assertEquals(ArrayUtils.contains(users,user1),true),
                ()->Assertions.assertEquals(ArrayUtils.contains(users,user2),true),
                ()->Assertions.assertEquals(ArrayUtils.contains(users,user3),true),
                ()->Assertions.assertEquals(ArrayUtils.contains(users,user4),true)
        );
    }

}
