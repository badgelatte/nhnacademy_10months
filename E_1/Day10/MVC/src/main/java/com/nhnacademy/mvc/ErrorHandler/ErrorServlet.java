package com.nhnacademy.mvc.ErrorHandler;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nhnacademy.mvc.ErrorHandler.RequestDispatcher.*;
import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;
@Slf4j
@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        log.info("status_code");

        //todo exception_type
        req.setAttribute("exception_type",req.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("exception_type");

        //todo message
        req.setAttribute("messsage",req.getAttribute(ERROR_MESSAGE));
        log.info("messsage");

        //todo exception
        req.setAttribute("exception",req.getAttribute(ERROR_EXCEPTION));
        log.info("exception");

        //todo request_uri
        req.setAttribute("request_uri",req.getAttribute(ERROR_REQUEST_URI));
        log.info("request_uri");

        //todo /error.jsp forward 처리
        RequestDispatcher rd = req.getRequestDispatcher("/student/error.jsp");
        rd.forward(req,resp);
    }

}