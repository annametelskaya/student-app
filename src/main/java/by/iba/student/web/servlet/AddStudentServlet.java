package by.iba.student.web.servlet;

import by.iba.student.common.Data;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("students", Data.STUDENTS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addstudent.jsp");
        dispatcher.forward(req, resp);
    }
}