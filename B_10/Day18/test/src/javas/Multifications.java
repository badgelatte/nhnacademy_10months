package javas;
public class Multifications {
    int i = 2;

    public static void multification(int times) {
        for (int i = 1; i < 10; i++) {
            System.out.println(times + " * " + i + " = " + i * times);
        }
    }

    public static void multificationTable() {
        for(int i = 2; i < 10; i++) {
            multification(i);
        }
    }

    public static void main(String[] args) {
        multificationTable();
    }
}
