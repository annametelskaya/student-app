package by.iba.student.web.servlet;

import by.iba.student.Repository.ProfessorRepository;
import by.iba.student.Repository.StudentRepository;
import by.iba.student.common.Data;
import by.iba.student.common.Marks;
import by.iba.student.common.Professor;
import by.iba.student.common.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarksServelt extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private StudentRepository studentRepository;
    private ProfessorRepository professorRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (StudentRepository) sc.getAttribute("studentRepository");
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("marks", Data.MARKS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/marks.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("selectedStudent");
        String professorId = req.getParameter("selectedProfessor");
        String mark = req.getParameter("mark");
        String date = req.getParameter("date");
        String comment = req.getParameter("comment");
        Data.MARKS.add(new Marks(studentRepository.findStudentById(studentId), professorRepository.findProfessorById(professorId), mark, date, comment));
        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
