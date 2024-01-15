package com.nhnacademy.mvc.Update;

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

@Slf4j
@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 확인
        if(req.getParameter("id")==null) {
            log.info("해당하는 아이디가 없습니다.");
            resp.sendRedirect("/student/list");
            return;
        }
        //todo 학생조회
        Student student = studentRepository.getStudentById(req.getParameter("id"));
        req.setAttribute("student",student);

        //todo forward : /student/register.jsp
        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo null check
        if(req.getParameter("id")!= null ||
                req.getParameter("name")!=null ||
                req.getParameter("gender")!=null ||
                req.getParameter("age")!=null) {
            //todo student 저장
            Student student = studentRepository.getStudentById(req.getParameter("id"));
            student.setName(req.getParameter("name"));
            String genderString = req.getParameter("gender");
            Gender gender;
            if(genderString.equals("남")) {
                gender = Gender.M;
            }
            gender=Gender.F;
            student.setGender(gender);
            student.setAge(Integer.parseInt(req.getParameter("age")));
            studentRepository.update(student);

            //todo /student/view?id=student1 <-- redirect
            resp.sendRedirect("/student/view?id="+req.getParameter("id"));
        }
    }
}