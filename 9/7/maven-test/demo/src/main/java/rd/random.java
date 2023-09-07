package rd;

import org.apache.commons.math3.random.RandomDataGenerator;

public class random {
    public static void main(String[] args) {
        RandomDataGenerator random = new RandomDataGenerator();
        int rand = random.nextInt(0, 100);
        System.out.println("random : " + rand);
    }
}