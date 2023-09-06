import java.util.*;     // 2
import static java.util.Collections.*;      // 3

public class PackageTest{
    public static void main(String[] args) {
        java.util.List<String> list1 = new java.util.LinkedList<>(null); // 1
        List<Integer> list2 = new ArrayList<>();    // 2
        Collections.sort(list1, null);      // 2
        sort(list2, null);      // 3
    }
}