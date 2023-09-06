public class LoggerTest {
    public static int sum(int a, int b) {
        MyLogger.getLogger().info("덧셈 수행 : " + a + " + " + b);
        //info 대신 다른 걸 설정하면 다르게 나온다 -> ex. warning 설정 시 warning위로는 나오지만 아래는 안나온다
        return a + b;
    }

    public static double divide (int a, int b) {
        if( b== 0 ) {
            MyLogger.getLogger().warning("나눗셈 에러 발생!");
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }

    public static void main(String[] args){
        int a = 1;
        int b = 2;
        System.out.println(sum(a, b));
        System.out.println(divide(a, 0));
    }
}
