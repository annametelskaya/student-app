package by.iba.student.web.servlet;

import by.iba.student.Repository.Repository;
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
    private Repository<Integer, Professor> professorRepository;
    private Repository studentRepository;
    private Repository<Integer, Subject> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (Repository) sc.getAttribute("studentRepository");
        this.professorRepository = (Repository<Integer, Professor>) sc.getAttribute("professorRepository");
        this.subjectRepository = (Repository<Integer, Subject>) sc.getAttribute("subjectRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", this.studentRepository.findAll());
        req.setAttribute("professors", this.professorRepository.findAll());
        req.setAttribute("subjects",this.subjectRepository.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addmarks.jsp");
        dispatcher.forward(req, resp);
    }
}
