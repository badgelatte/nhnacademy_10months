public class Main {

    private static double correctCount = 0;

    private static final String[][] TESTCASES = new String[][] {
            {"123", "123", "246"},
            {"1000", "1", "2"},
            {"456", "789", "1461"},
            {"5", "5", "1"},
            {"11112", "54321", "65433"},
            {"3829", "1300", "4139"}
    };

    public static void main(String[] args) {
        for (int i = 0; i < TESTCASES.length; i++)
            System.out.println("Testcase " + i + " = " + test(TESTCASES[i][0], TESTCASES[i][1], TESTCASES[i][2]));

        System.out.println("정답률 = " + (int) (correctCount / TESTCASES.length * 100) + "%");
    }

    private static boolean test(String input, String input2, String answer) {
        int first = Integer.parseInt(input);
        int second = Integer.parseInt(input2);

        boolean res = String.valueOf(solution2(first, second)).equals(answer);
        if (res)
            correctCount++;
        return res;
    }

    private static int solution(int first, int second) {    // char 방식
        char[] reversef = {};
        char[] reverses;
        
        if(first > 10) {
            String a = Integer.toString(first);        // first를 string으로 변환
            reversef = a.toCharArray();

        }
        return 0;
    }

    // solution
    public static int solution2(int first, int second) {    // int 형 사용
        int result = 0;
        int result1 = 0;
        int result2 = 0;
        int reversedResult = 0;
        int output = 0;

        // 첫번째 수 뒤집기
        int reversedFirst = first % 10;     // first = 123, reversedFirst = 3
        first /= 10;                        // first = 12
        result1 += reversedFirst;            // result = 3
        // result *= 100;                   // result = 300

        while(first > 0) {
            reversedFirst = first % 10;     // first = 12, reversedFirst = 2
            first /= 10;                    // first = 1
            result1 *= 10;                   // result = 30
            result1 += reversedFirst;        //  result = 32
        }
        System.out.println("first : " + result1);


        // 두번째 수 뒤집기
        int reversedSecond = second % 10;
        second /= 10;
        result2 += reversedSecond;

        while(second > 0) {
            reversedSecond = second % 10;     // first = 12, reversedFirst = 2
            second /= 10;                    // first = 1
            result2 *= 10;                   // result = 30
            result2 += reversedSecond;        //  result = 32
        }

        System.out.println("second : " + result2);

        // 뒤집은 수 더하기
        result = result1 + result2;     // 321 + 321 = 642

        // 더한 수를 다시 뒤집기
        while(result > 0) {
            reversedResult = result % 10;     // result = 642, reversedResult = 2 // result = 64, reversedResult = 4
            result /= 10;                        // result = 64                     // result = 6
            output *= 10;                       // output = 0                      // output = 20
            output += reversedResult;           // output = 2                       // output = 24

        }
        System.out.println(output);


        return output;
    }
    
}