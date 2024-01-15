package com.nhnacademy.mvc.Register;

import com.nhnacademy.mvc.main.Gender;
import com.nhnacademy.mvc.main.Student;
import com.nhnacademy.mvc.main.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo  init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo  /student/register.jsp forward 합니다.
        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // null check
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String genderString = req.getParameter("gender");
        String age = req.getParameter("age");

        if (id.isEmpty()|| name.isEmpty()||genderString.isEmpty()||age.isEmpty()){
            log.info("입력하지 않은 값 존재");
            resp.sendRedirect("/student/register");
            return;
        }

        Gender gender;
        if(genderString.equals("남")) {
            gender = Gender.M;
        }
        gender=Gender.F;

        // save 구현
        Student student = new Student(id, name, gender, Integer.parseInt(age));
        studentRepository.save(student);

        // redirect /student/view?id=student1
        resp.sendRedirect("/student/view?id="+id);
    }

}