package example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam02 {
    public static void main(String[] args) {
        try {
            String jsonText = "{\"name\" : \"nhn\"}";
            JSONObject object = new JSONObject(jsonText);

            System.out.println(object.toString());
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

}
