package com.nhnacademy.mvc.View;

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
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo id null check
        String id;
        if((id = req.getParameter("id"))!=null){
            //todo student 조회
            Student student = studentRepository.getStudentById(id);
            req.setAttribute("student",student);

            //todo /student/view.jsp <-- forward
            RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
            rd.forward(req,resp);
        }
    }

}