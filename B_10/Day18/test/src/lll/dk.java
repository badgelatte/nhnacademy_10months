package lll;

import java.util.Arrays;

@FunctionalInterface

interface Map {
    int adder(int a);
}

public class dk {

    public int[] adder(Map list, int[] a) {
        for (int i = 0; i < a.length; i++) {
            // a[i] = list.adder(i);
            a[i] = list.adder(a[i]);
            
        }
        return a;
    }

    public static void main(String[] args) {
        int[] oneToTen = { 2,4,5,6,7,8,9,10,11};
        dk ep = new dk();

        int[] m = ep.adder((a) -> a + 1, oneToTen);
        System.out.println(Arrays.toString(m));

    }
}