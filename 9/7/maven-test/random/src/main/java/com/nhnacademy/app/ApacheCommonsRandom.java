package com.nhnacademy.app;
import org.apache.commons.math3.random.RandomDataGenerator;

public class ApacheCommonsRandom {
    public static void main(String[] args) {
        RandomDataGenerator randomGenerator = new RandomDataGenerator();
        int rand = randomGenerator.nextInt(0, 100);
        System.out.println("random: " + rand);
    }
}
