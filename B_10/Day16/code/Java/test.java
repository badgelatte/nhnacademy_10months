package Java;
public class test{
    public static void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }
    public static void main(String[] args) {
        int i = 5;
        int j = 6;

        // int temp = i;
        // i = j;
        // j = temp;
        swap(5, 6);
        System.out.print(i);
        System.out.println(", " + j);
    }
}