package javas;

public class gogodan {
    public static int multipifcation(int times, int number) {
        if(number > 9) {
            return 0;
        }
        else {
            Thread.currentThread().setName(""+ number);
            System.out.println(times + " * " + number + " = " + times * number);
            multipifcation(times, number+1);
        }
        return 0;
    }

    public static int multipificationTable(int count, int limit) {
        if(count > limit) {
            return 0;
        }
        else {
            Thread.currentThread().setName(count+"단");
            multipifcation(count, 1);
            System.out.println();
            return multipificationTable(count + 1, limit);
        }
    }

    public static void main(String[] args) {
        // multipifcation(2,1);
        multipificationTable(2,19);
    }
}
// multipletable도 만들기 이거도 recursion으로