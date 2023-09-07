package com.nhnacademy.app;
import org.apache.commons.lang3.StringUtils;

public class MyStringUnits {

    public static boolean isEmpty1(String str) {

        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isEmpty2(String str) {
        return StringUtils.isEmpty(str);
    }
}
