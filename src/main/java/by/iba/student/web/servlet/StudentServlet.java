package by.iba.student.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.common.Data;
import by.iba.student.common.Student;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", Data.STUDENTS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
//        String group = req.getParameter("groupNumber");
        Data.STUDENTS.add(new Student(firstName, secondName));
        doGet(req, resp);

    }


}
