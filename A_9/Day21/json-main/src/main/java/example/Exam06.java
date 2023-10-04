package example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam06 {
    public static void main(String[] args) {
        try {
            JSONObject customer = new JSONObject();
            customer.put("name", "nhn");
            customer.put("age", 20);
            System.out.println(customer);

            String name = customer.getString("name");
            System.out.println(name);

            int age = customer.getInt("age");
            System.out.println(age);
        } catch (JSONException e) {
            System.err.println(e);
        }

    }
}
