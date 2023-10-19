package javas;


public class changeLetter {
    public static String changeLetter(String target, String result, int loc){
        if(result.length() == target.length()){
            return result;
        }
        else {
            int letter = target.charAt(loc);
            if(letter < 97 || letter > 122) {
                //int -> char로 바꾸기
                result += (char)(letter + 32);
            }
            else {
                result += (char)(letter - 32);
            }
            return changeLetter(target, result, loc+1);
        }
    }

    public static void main(String[] args) {
        String s = "aBcDe";

        String answer = "AbCdE";
        
        String result = changeLetter(s, "", 0);
        
        if (answer.equals(result)){
            System.out.println("well done");
        }
        else{
            System.out.println("damm!");
            System.out.println(result + "is wrong result!!");
        }
    }
}