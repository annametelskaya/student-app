package by.iba.student.web.servlet;

import by.iba.student.common.Data;
import by.iba.student.common.Professor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfessorsServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("professors", Data.PROFESSORS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/professors.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String fatherName = req.getParameter("fatherName");
        String birthDate = req.getParameter("birthDate");
//        String group = req.getParameter("groupNumber");
        Data.PROFESSORS.add(new Professor(firstName, secondName, fatherName, birthDate));
        doGet(req, resp);

    }
}
