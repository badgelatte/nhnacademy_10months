package com.nhnacademy.mvc.main;

import com.nhnacademy.mvc.main.Student;
import com.nhnacademy.mvc.main.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStudentRepository implements StudentRepository {
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    //학생-등록
    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(),student);
    }
    //학생-수정
    @Override
    public void update(Student student) {
        studentsMap.replace(student.getId(),student);
    }
    //학생-삭제
    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }
    //학생-조회_by id
    @Override
    public Student getStudentById(String id) {
        return studentsMap.get(id);
    }
    //학생-리스트
    @Override
    public List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>(studentsMap.values());
        return studentList;
    }
    //학생-아이디 존재여부
    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
}
