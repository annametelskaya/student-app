package by.iba.student.web.servlet;

import by.iba.student.filter.ProfessorFilter;
import by.iba.student.filter.SubjectFilter;
import by.iba.student.repository.Repository;
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

    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.subjectRepository = (Repository<Integer, Subject, SubjectFilter>) sc.getAttribute("subjectRepository");
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String professor = req.getParameter("sortByProf");
        String name = req.getParameter("sortByName");
        String hours = req.getParameter("sortByHours");
        SubjectFilter subjectFilter = new SubjectFilter();
        if (professor != null) {
            subjectFilter.setProfessor(professor);
        }
        if (name != null) {
            subjectFilter.setName(name);
        }
        if (hours != null) {
            subjectFilter.setHours(hours);
        }
        req.setAttribute("subjects", this.subjectRepository.findAll(subjectFilter));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/subjects.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String professorId = req.getParameter("selectedProfessor");
        String name = req.getParameter("subjectName");
        String hours = req.getParameter("hours");
        System.out.println(professorId);
        Professor professor = this.professorRepository.findById(Integer.valueOf(professorId));
        this.subjectRepository.create(new Subject(name, Integer.valueOf(hours), professor));
        doGet(req, resp);
    }

    @Override
    public void destroy() {

    }
}
