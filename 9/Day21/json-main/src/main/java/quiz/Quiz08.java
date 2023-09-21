package quiz;

import org.json.JSONArray;
import org.json.JSONException;

public class Quiz08 {
    public static void main(String[] args) {
        try {
            JSONArray jsonArray = new JSONArray();

            jsonArray.put("true");
            jsonArray.put(1);
            jsonArray.put(true);

            int index = -1;
            for (int i = 0; i < jsonArray.length(); i++) {
                Object item = jsonArray.get(i);

                if (item instanceof Integer) {
                    index = i;
                    break;
                }
            }

            if (index < 0) {
                System.out.println("1을 찾을 수 없습니다.");
            } else {
                System.out.println("1은 " + (index + 1) + "번째에 위치합니다.");
            }
        } catch (JSONException e) {
            System.err.println(e);
        }

    }

}
