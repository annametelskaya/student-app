package by.iba.student.web.servlet;

import by.iba.student.filter.MarksFilter;
import by.iba.student.filter.ProfessorFilter;
import by.iba.student.filter.StudentFilter;
import by.iba.student.filter.SubjectFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Marks;
import by.iba.student.common.Professor;
import by.iba.student.common.Student;
import by.iba.student.common.Subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MarksServelt extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private Repository<Integer, Marks, MarksFilter> marksRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.marksRepository = (Repository<Integer, Marks, MarksFilter>) sc.getAttribute("marksRepository");
        this.studentRepository = (Repository<Integer, Student, StudentFilter>) sc.getAttribute("studentRepository");
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
        this.subjectRepository = (Repository<Integer, Subject, SubjectFilter>) sc.getAttribute("subjectRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("marks", this.marksRepository.findAll(new MarksFilter()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/marks.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subjectId = req.getParameter("selectedSubject");
        String studentId = req.getParameter("selectedStudent");
        String professorId = req.getParameter("selectedProfessor");
        String mark = req.getParameter("mark");
        String date = req.getParameter("date");
        String comment = req.getParameter("comment");
        Subject subject = this.subjectRepository.findById(Integer.valueOf(subjectId));
        Student student = this.studentRepository.findById(Integer.valueOf(studentId));
        Professor professor = this.professorRepository.findById(Integer.valueOf(professorId));
        try {
            this.marksRepository.create(new Marks(subject, student, professor, Double.valueOf(mark),
                    new SimpleDateFormat("yyyy-MM-dd").parse(date), comment));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
