package javas.lambdass;

interface BinaryOp {
    int bind(int a, int b);
}

// class Adder implements BinaryOp{
//     public int bind(int a, int b) {
//         return a + b;
//     }    
// }

// class Mult implements BinaryOp{
//     public int bind(int a, int b) {
//         return a * b;
//     }    
// }

public class Algorithm {

    public int exam (BinaryOp a, int b, int c){
        return a.bind(b, c); // -> 연산의 객체화
        // BinaryOp class로 받아서 BinaryOp의 add를 return 하겠다
    }

    public static void main(String[] args) {
        Algorithm a = new Algorithm();
        a.exam((x, y) -> x + y, 1, 1);
    }
}


//------------------------------------------------------------------------------------------------
@FunctionalInterface
interface BinaryOps {
    int df(int a, int b);

    // int ls (int am, int bm);
}
// 정의만 되어있고 구현은 되어 있는게 없다 = interface 
// && interface가 1개면 functionalInterface


class NNew{
    public  int exams(BinaryOps a, int z, int x) {
        return a.df(z, x);
    }

    public static void main(String[] args) {
        NNew n = new NNew();
        System.out.println(n.exams((a, b) -> a * b, 5, 100));
    }
} 