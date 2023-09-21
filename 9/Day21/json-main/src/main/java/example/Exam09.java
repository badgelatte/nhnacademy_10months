package example;

import org.json.JSONException;
import org.json.JSONTokener;

public class Exam09 {

    public static void main(String[] args) {
        try {
            JSONTokener tokener = new JSONTokener(System.in);
            while (tokener.more()) {
                System.out.println(tokener.nextValue());
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}
