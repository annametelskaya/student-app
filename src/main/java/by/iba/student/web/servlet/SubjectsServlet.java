package by.iba.student.web.servlet;

import by.iba.student.Repository.ProfessorRepository;
import by.iba.student.Repository.SubjectRepository;
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
    private SubjectRepository subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.subjectRepository = (SubjectRepository) sc.getAttribute("subjectRepository");
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("subjects", this.subjectRepository.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String professorId = req.getParameter("selectedProfessor");
        String name = req.getParameter("subjectName");
        String hours = req.getParameter("hours");
        this.subjectRepository.create(new Subject(name, hours, (this.professorRepository.findProfessorById(professorId).getFirstName() + " " +
                this.professorRepository.findProfessorById(professorId).getSecondName() + " " +
                this.professorRepository.findProfessorById(professorId).getFatherName())));
//      this.subjectRepository.create(new Subject(name, hours, professorId));
        doGet(req, resp);
    }

    @Override
    public void destroy() {

    }
}
