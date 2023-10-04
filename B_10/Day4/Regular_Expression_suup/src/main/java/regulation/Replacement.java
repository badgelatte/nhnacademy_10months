package regulation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacement {
    public static void main(String[] args) {
        // System.out.println(runTest("hi", "hihi"));
        System.out.println(runTest("(hi)+", "hihi"));
    }

    public static int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        
        System.out.println("LookinigAt : " + matcher.lookingAt());
        // 
        System.out.println("matches : " + matcher.matches());        
        int matches = 0;
        while (matcher.find()) {
            System.out.println(matcher.start() + " : " + matcher.end());
            matches++;
        }
        return matches;
    }
}