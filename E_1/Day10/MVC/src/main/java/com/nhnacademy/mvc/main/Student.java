package com.nhnacademy.mvc.main;

import com.nhnacademy.mvc.main.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDateTime createdAt;

    public Student(String id, String name, Gender gender, int age){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        createdAt = LocalDateTime.now();
    }
}
