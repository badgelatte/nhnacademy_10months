public class Main {
    private static double correctCount = 0;
    private static final String[][] TESTCASES = new String[][] {
        { "MANGO", "mango" }, 
        { "peach", "PEACH" },
        { "Apple", "aPPLE" }, 
        { "baNANa", "BAnanA" }, 
        { "Believe In YourSelF", "bELIEVE iN yOURsELf" }, 
        { "THiS is how LIFE is!!", "thIs IS HOW life IS!!" }, 
        { "Li^^ve  p$osi&TIVE*", "lI^^VE  P$OSI&tive*" }, 
        { "%tiMe~~~~ iS>.< gggggolD ?:)", "%TImE~~~~ Is>.< GGGGGOLd ?:)" }
    };

    public static void main(String[] args) {
        for (int i = 0; i < TESTCASES.length; i++) 
            System.out.println("Testcase " + i + " = " + test(TESTCASES[i][0], TESTCASES[i][1]));

        System.out.println("정답률 = " + (int)(correctCount / 8 * 100) + "%");
    }

    private static boolean test(String input, String answer) {
        boolean res = solution2(input).equals(answer);
        if (res)
            correctCount++;
        return res;
    }


    // solution
    public static String solution(String input) {   // for each문으로 작성 (내가 만든 거!)
        int a;
        String output = "";
        // 문자열 문자로 나누기
        char[] c1 = input.toCharArray();
        for(char c2 : c1) {   // 변환
            a = (int)c2;
            if(a >= 65 && a <= 90) {
                a = a + 32;
            }
            else if (a >= 97 && a <= 122){
                a = a - 32;
            }
            c2 = (char) a;

            // 변환한 b 값을 g에 넣기
            output += c2;

        }

        System.out.println(output);
        return output;
    }

    public static String solution2(String input) { // for문으로 작성
        char[] array = input.toCharArray();
        for(int i = 0; i< input.length(); i++) {
            if (array[i] >= 65 && array[i] <= 90){  // 대문자일 경우
                array[i] +=32;  // 소문자로 바꿈
            }
            else if(array[i] >= 97 && array[i] <= 122){  // 소문자일 경우
                array[i] -= 32; // 대문자로 바꿈
            }
        }
        /* String output = new String(array);
        System.out.println(output);
        return output; */   // 위 문단과 아래는 동일
        return String.valueOf(array);
        
    }
}