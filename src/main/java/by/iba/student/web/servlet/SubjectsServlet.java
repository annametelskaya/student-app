package by.iba.student.web.servlet;

import by.iba.student.Repository.ProfessorRepository;
import by.iba.student.common.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private ProfessorRepository professorRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", Data.SUBJECTS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String professorId = req.getParameter("selectedProfessor");
        String name = req.getParameter("subjectName");
        String hours = req.getParameter("hours");
        Data.SUBJECTS.add(new Subject(name, hours, professorRepository.findProfessorById(professorId)));
        doGet(req, resp);
    }

    @Override
    public void destroy() {

    }
}
