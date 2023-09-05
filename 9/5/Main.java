public class Main    {

    public static void main(String[] args) {
        int[] input = {0, 3, 2, 624, 339, 13, 45232};
        String[] output = {"0", "11", "10", "1001110000", "101010011", "1101", "1011000010110000"};

        System.out.printf("Out of %d Cases, %1.0f were correct", input.length, test(input, output));
    }

    public static double test(int[] input, String[] output) {
        double score = 0;

        for (int i = 0; i < input.length; i++) {
            System.out.printf("Case %d %n", i);

            String correct = output[i];
            System.out.printf("Input : %d \t -> Expected : %s %n",input[i], output[i]);

            String str = solution(input[i]);
            System.out.printf("Your Answer : %s %n", str);

            if (str.equals(correct)) {
                score++;
            }
        }

        return score;
    }

    public static String solution(int input){   //내가 한 거
        int tenToTwo;
        String output = "";

        if(input == 0){
            output = "0";
            return output;
        }

        for(int i = 0; input >0;i++){
            
            tenToTwo = input % 2;
            input = input / 2;
            output = Integer.toString(tenToTwo) + output;
        }

        return output;
        
    }

    public static String solution2(int input){  // 정답지
        String answer = "";
        do {
            answer = (input % 2) + answer;
            input /= 2;
        } while (input > 0);

        return answer;
    }

}
