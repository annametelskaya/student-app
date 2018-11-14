package by.iba.student.web.servlet;

import by.iba.student.Repository.ProfessorRepository;
import by.iba.student.Repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddMarkServelt extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private ProfessorRepository professorRepository;
    private StudentRepository studentRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (StudentRepository) sc.getAttribute("studentRepository");
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", this.studentRepository.findAll());
        req.setAttribute("professors", this.professorRepository.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addmarks.jsp");
        dispatcher.forward(req, resp);
    }
}
