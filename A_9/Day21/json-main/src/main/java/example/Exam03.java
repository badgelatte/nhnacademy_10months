package example;

import org.json.JSONException;
import org.json.JSONObject;

import example.exam03.Person;

public class Exam03 {
    public static void main(String[] args) {
        try {
            Person person = new Person("nhn");

            JSONObject object = new JSONObject(person);

            System.out.println(object);
        } catch (JSONException e) {
            System.out.println(e);
        }
    }
}
