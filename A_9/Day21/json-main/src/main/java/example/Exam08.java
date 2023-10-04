package example;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Exam08 {
    public static void main(String[] args) {
        try {
            String[] cities = new String[] { "서울", "부산", "광주", "대구", "대전", "인천", "울산" };
            JSONArray jsonArray = new JSONArray();
            for (String city : cities) {
                jsonArray.put(city);
            }

            JSONObject object = new JSONObject();
            object.put("도시", jsonArray);

            System.out.println(object.toString());
        } catch (JSONException e) {
            System.err.println(e);
        }
    }

}
