package quiz;

import org.json.JSONException;
import org.json.JSONObject;

public class Quiz05 {

    public static void main(String[] args) {
        try {
            String jsonString = "{\"model\":\"K2\",\"power\":10}";
            JSONObject jsonObject = new JSONObject(jsonString);

            System.out.println("Model: " + jsonObject.getString("model"));
            System.out.println("Power: " + jsonObject.getInt("power"));
            System.out.println("Flyable: " + jsonObject.optBoolean("flyable", false));

        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}