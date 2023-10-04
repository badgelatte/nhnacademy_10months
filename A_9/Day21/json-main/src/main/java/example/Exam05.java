package example;

import org.json.JSONException;
import org.json.JSONObject;

public class Exam05 {
    public static void main(String[] args) {
        try {
            JSONObject customer = new JSONObject();
            customer.put("name", "nhn");
            customer.put("age", 20);
            System.out.println(customer);

            Object name = customer.get("name");
            System.out.println(name.getClass().getTypeName());
            System.out.println(name);

            Object age = customer.get("age");
            System.out.println(age.getClass().getTypeName());
            System.out.println(age);

        } catch (JSONException e) {
            System.err.println(e);
        }

    }
}
