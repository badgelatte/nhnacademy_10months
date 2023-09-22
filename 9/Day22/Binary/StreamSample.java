import java.util.ArrayList;
import java.util.*;

public class StreamSample {
    // public static int[] getIntegers(int size) {
    public static int[] getIntegers(int size) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static List<Integer> getIntegerList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list;
    }

    public static List<Guitarist> getGuitarist() {
        List<Guitarist> list = new ArrayList<>();
        list.add(new Guitarist.Builder()
        .no(1)
        .name("Randy Rhoads")
        .teamName("Quite Riot")
        .guitar("Les paul")
        .build());
        list.add(new Guitarist.Builder()
        .no(2)
        .name("Jimmy Page")
        .teamName("Led Zeppelin")
        .guitar("Les paul")
        .build());
        list.add(new Guitarist.Builder()
        .no(3)
        .name("Deep ")
        .teamName("")
        .guitar("Stratocaster")
        .build());

        return list;
    }
}

class Test {
    public static void main(String[] args) {
        int[] array = StreamSample.getIntegers(100);

        for (int i : array) {
            if(i % 2 == 0)
                System.out.println(i);
        }

        List<Integer> list = StreamSample.getIntegerList(100);

        for (int i : list) {
            if(i % 2 == 0)
                System.out.println(i);
        }

        List<Guitarist> list2 = StreamSample.getGuitarist();

        for (Guitarist g : list2) {
            if(g.guitar == "Stratocaster") {
                System.out.println(g);
            }
        }
    }
}
