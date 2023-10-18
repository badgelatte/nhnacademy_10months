package javas;

import java.util.ArrayList;
import java.util.List;

public class GetPrimeFacotrs {
    public static List primeFactors(int number) {
        // int[] list = new int[number];
        List list = new ArrayList<>();

        for(int i = 2; i < 10; i++){
            if (number % i == 0) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List result = primeFactors(10);
        
        System.out.println(result);        
    }
}
