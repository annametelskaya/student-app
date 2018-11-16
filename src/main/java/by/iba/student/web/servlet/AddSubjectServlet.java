package by.iba.student.web.servlet;

import by.iba.student.filter.ProfessorFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Professor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddSubjectServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("professors", this.professorRepository.findAll(new ProfessorFilter()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addsubject.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}

