package by.iba.student.web.servlet;

import by.iba.student.common.Student;
import by.iba.student.filter.ProfessorFilter;
import by.iba.student.filter.StudentFilter;
import by.iba.student.filter.SubjectFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Professor;
import by.iba.student.common.Subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMarkServelt extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (Repository<Integer, Student, StudentFilter>) sc.getAttribute("studentRepository");
        this.subjectRepository = (Repository<Integer, Subject, SubjectFilter>) sc.getAttribute("subjectRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", this.studentRepository.findAll(new StudentFilter()));
        req.setAttribute("subjects", this.subjectRepository.findAll(new SubjectFilter()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addmarks.jsp");
        dispatcher.forward(req, resp);
    }
}
