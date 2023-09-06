import java.util.*;
import static java.util.Collections.*;

public class PackageTest{
    public static void main(String[] args) {
        java.util.List<String> list1 = new java.util.LinkedList<>(null);
        List<Integer> list2 = new ArrayList<>();
        Collections.sort(list1, null);
    }
}