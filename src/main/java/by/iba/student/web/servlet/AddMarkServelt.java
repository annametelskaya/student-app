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



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addmarks.jsp");
        dispatcher.forward(req, resp);
    }
}
