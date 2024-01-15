package com.nhnacademy.mvc.Listener;


import com.nhnacademy.mvc.main.Gender;
import com.nhnacademy.mvc.main.MapStudentRepository;
import com.nhnacademy.mvc.main.Student;
import com.nhnacademy.mvc.main.StudentRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApplicationListener  implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();

        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            String id = "student" + i;
            String name = "아카데미" + i;
            Gender gender = i%2 == 0 ? Gender.M : Gender.F;

            // 나이 : random 처리 : 20~30
            //(int) Math.random() * (최댓값-최소값+1) + 최소값
            int age = (int)(Math.random()*(30-20+1)) + 20;
            studentRepository.save(new Student(id, name, gender, age));
        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository",studentRepository);
    }
}
