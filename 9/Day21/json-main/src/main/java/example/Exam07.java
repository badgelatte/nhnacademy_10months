package example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam07 {
    public static void main(String[] args) {
        try {
            JSONObject customer = new JSONObject();
            customer.put("name", "nhn");
            customer.put("age", 20);
            System.out.println(customer);

            String name = customer.getString("name");
            System.out.println(name);

            String age = customer.getString("age");
            System.out.println(age);
        } catch (JSONException e) {
            System.err.println(e);
        }

    }
}
