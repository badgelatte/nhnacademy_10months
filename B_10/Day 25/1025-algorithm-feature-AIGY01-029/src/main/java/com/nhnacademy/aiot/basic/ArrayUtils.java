package com.nhnacademy.aiot.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ArrayUtils {
    public ArrayUtils(){
        throw new IllegalStateException();
    }

    public static boolean equals(Object[] objects1 , Object[] objects2 ){
        if(objects1 == null || objects2 == null) {
            throw new IllegalArgumentException();
        }
        // User 아닌 class 비교
        if(!objects1[0].getClass().equals(User.class)) {
            for (int i = 0; i < objects2.length; i++) {
                if(objects1.length != objects2.length || !objects1[i].equals(objects2[i])){
                    return false;
                }
            }
        }
        else {  // class가 User인 거 비교
            for (int i = 0; i < objects1.length; i++) {
                log.info("ojects1 ojects2 : {} {}", ((User)objects1[i]).hashCode(), ((User)objects2[i]).hashCode());
                if (objects1.length != objects2.length || !((User)objects1[i]).equals((User)objects2[i])){
                    return false;
                }
            }
        }
        return true;
    }
}
