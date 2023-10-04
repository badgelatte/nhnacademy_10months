package quiz;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Quiz07 {

    public static void main(String[] args) {
        try {
            JSONArray animal = new JSONArray();

            JSONArray birdArray = new JSONArray();
            birdArray.put("갈매기");
            birdArray.put("참새");
            birdArray.put("펭귄");
            JSONObject bird = new JSONObject();
            bird.put("조류", birdArray);
            animal.put(bird);

            JSONArray mammalArray = new JSONArray();
            mammalArray.put("사자");
            mammalArray.put("호랑이");
            mammalArray.put("말");
            JSONObject mammal = new JSONObject();
            mammal.put("포유류", mammalArray);
            animal.put(mammal);

            JSONObject object = new JSONObject();

            object.put("동물", animal);

            System.out.println(object.toString());
        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}
