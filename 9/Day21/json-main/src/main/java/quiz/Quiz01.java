package quiz;

import org.json.JSONException;
import org.json.JSONObject;

public class Quiz01 {

    public static void main(String[] args) {

        try {
            JSONObject addressObject = new JSONObject();
            addressObject.put("code", 13487);
            addressObject.put("city", "Seongnam");

            JSONObject object = new JSONObject();
            object.put("name", "nhn");
            object.put("address", addressObject);

            System.out.println(object.toString());
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}