package javas.lambdass;

@FunctionalInterface
public interface Lambda {
    int exam(int a, int b);
}

class Calc {

    // @Override
    // public void exam(int a, int b) {
    //     int lambda;
    //     lambda = a * b;
    // }
    public int claculate (Lambda sin, int a, int b) {
        return sin.exam(a, b);
    }

    public static void main(String[] args) {
        Calc calc = new Calc();
        int result = calc.claculate((a, b) -> a * b, 1, 2);

        System.out.println(result);
        // Lambda sdf = (int a, int b) -> a * b;
        // System.out.println(sdf.exam(3, 3));
    }
}