package com.nhnacademy.test;

import java.util.UUID;

import com.github.f4b6a3.uuid.UuidCreator;

public class TestUUID {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            UUID uuid = UuidCreator.getTimeBased();

            System.out.println(String.format("%s : %d", uuid, uuid.timestamp()));
        }
    }
}
