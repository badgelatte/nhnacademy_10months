package example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam01 {

    public static void main(String[] args) {

        try {
            JSONObject object = new JSONObject();
            object.put("name", "nhn");

            System.out.println(object.toString());
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}