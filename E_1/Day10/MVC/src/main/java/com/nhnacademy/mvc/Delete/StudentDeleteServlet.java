package com.nhnacademy.mvc.Delete;

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
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo get parameter  : id , id가 존재하지 않을경우 throw new RuntimeException("...")
        log.info("student: {} id: {}", req.getParameter("student"), req.getParameter("id"));
        if(req.getParameter("id")!= null&&studentRepository.existById(req.getParameter("id"))){
            String id = req.getParameter("id");
            studentRepository.deleteById(id);
            //todo /student/list <-- redirect
            resp.sendRedirect("/student/list");
        }
        else {
            log.info("삭제 실패");
            throw new RuntimeException("삭제하고자하는 student가 없습니다.");
        }
    }
}