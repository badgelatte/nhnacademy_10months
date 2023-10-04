import java.util.*;
import java.util.stream.*;

public class StreamSamplStream {
    // IntStream - 배열은 Stream을 쓰기 위해 IntStream이라는 것을 따로 만들었다. 
    // -> int[]로 primitive type은 ~~Stream을 사용한다
    // 반대로 Stream<??> 에선 generic이 reference type을 사용하기 때문에 Stream<>으로 사용이 가능하다
    
    
    // public static int[] getIntegers(int size) {
    public static IntStream getIntegers(int size) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return Arrays.stream(array);
    }

    public static Stream<Integer> getIntegerList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        return list.stream();
    }

    public static Stream<Guitarist> getGuitarist() {
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
            .name("Ritch B")
            .teamName("Deep Purple")
            .guitar("Stratocaster")
            .build());

        return list.stream();
    }
}

class Test {
    public static void main(String[] args) {

        IntStream intstream = StreamSamplStream.getIntegers(100)
            .filter(x -> x % 2 == 0)
            .map(x -> x + 10);
            // filter가 functionalinterface라 가능
        // intstream.forEach(System.out::println); -> 아래의 lambda형식과 같은 의미
        intstream.forEach(x-> System.out.println(x));
        
        Stream<Integer> list = StreamSamplStream.getIntegerList(100)
            .filter(x -> x % 2 == 0);

        list.forEach(System.out::println);
        /* for (int i : list) {
            if(i % 2 == 0)
                System.out.println(i);
        }
 */
        // stream - 열린게 목록이든, 배열이든 열 수 있다.
        // 완전히 똑같은 방법으로 고칠 수 있는게 Stream이다 -> ...? 
        // => 연결되어있을 때 목록이든 network이든 목록에 대한 통로를 열어서 데이터를 처리하는 일관된 방법이 stream이다

        // filter - 조건문이 맞을 경우 true를 반환
        // map - 고치고 싶을 땐 
        Stream<Guitarist> list2 = StreamSamplStream.getGuitarist()
            .filter(x -> x.guitar == "Stratocaster");
            // .map(x -> x.guitar + " Type");

        list.forEach(System.out::println);
       /*  for (Guitarist g : list2) {
            if(g.guitar == "Stratocaster") {
                System.out.println(g);
            }
        } */
    }
}
