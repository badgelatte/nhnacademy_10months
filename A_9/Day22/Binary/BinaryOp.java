// interface에 연산하나여야 한다 -> FuctionalInterface
@FunctionalInterface
public interface BinaryOp{
    public int apply(int i, int j);
}

class Mult implements BinaryOp{
    public int apply(int i, int j){
        return i * j;
    }
}


class Algo{
    public static int calc(BinaryOp binder, int i, int j) {
        return binder.apply(i,j);
    }

    static BinaryOp adder = new BinaryOp() {
        public int apply(int i, int j) {
            return i + j;
        }
    };

    public static void main(String[] agrs) {
        calc((a, b) -> a % b,  1, 2);
    }
}