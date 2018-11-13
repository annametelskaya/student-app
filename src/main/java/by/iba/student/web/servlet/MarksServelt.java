package by.iba.student.web.servlet;

import by.iba.student.Repository.MarksRepository;
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
    private MarksRepository marksRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.marksRepository = (MarksRepository) sc.getAttribute("marksRepository");
        this.studentRepository = (StudentRepository) sc.getAttribute("studentRepository");
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("marks", this.marksRepository.findAll());
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
//      this.marksRepository.create(new Marks(studentId, professorId, mark, date, comment));
        this.marksRepository.create(new Marks((this.studentRepository.findStudentById(studentId).getFirstName() + " " +
                this.studentRepository.findStudentById(studentId).getSecondName()),
                (this.professorRepository.findProfessorById(professorId).getFirstName() + " " +
                        this.professorRepository.findProfessorById(professorId).getSecondName() + " " +
                        this.professorRepository.findProfessorById(professorId).getFatherName()), mark, date, comment));

        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
