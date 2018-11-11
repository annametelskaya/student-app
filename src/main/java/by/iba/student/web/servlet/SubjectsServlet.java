package by.iba.student.web.servlet;

import by.iba.student.common.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectsServlet extends HttpServlet {


    private static final long serialVersionUID = 6345194112526801506L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", Data.SUBJECTS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] professorInfo = req.getParameter("selectedProfessor").split(" ");
        Professor professor = Data.findProfessor(professorInfo[0], professorInfo[1], professorInfo[2]);
        String name = req.getParameter("subjectName");
        String hours = req.getParameter("hours");
        Data.SUBJECTS.add(new Subject(name, hours, professor));
        doGet(req, resp);

    }
}
