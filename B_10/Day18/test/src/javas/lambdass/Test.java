package javas.lambdass;

import java.util.ArrayList;
import java.util.List;

interface Map<T> {
    T apply(T element);
}

class CollectionUtils<T> {
    public Iterable<T> changeelements(Map<T> map, Iterable<T> iterable) {   // Iterable - 계속 반복한다는 의미
        List <T> result = new ArrayList<>();

        for (T t : iterable) {
            result.add(map.apply(t));
        }
        return result;
    }
}

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        CollectionUtils<Integer> collect = new CollectionUtils<>();
        System.out.println(collect.changeelements(x -> x+3 , list));
        
    }
}
