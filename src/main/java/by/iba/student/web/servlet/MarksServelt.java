package by.iba.student.web.servlet;

import by.iba.student.Repository.Repository;
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
    private Repository<Integer, Student> studentRepository;
    private Repository<Integer,Professor> professorRepository;
    private Repository<Integer,Marks> marksRepository;
    private Repository<Integer,Subject> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.marksRepository = (Repository<Integer, Marks>) sc.getAttribute("marksRepository");
        this.studentRepository = (Repository<Integer, Student>) sc.getAttribute("studentRepository");
        this.professorRepository = (Repository<Integer, Professor>) sc.getAttribute("professorRepository");
        this.subjectRepository = (Repository<Integer, Subject>) sc.getAttribute("subjectRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("marks", this.marksRepository.findAll());
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
            this.marksRepository.create(new Marks(subject.getName(), student.getFirstName() + " " + student.getSecondName(),
                    professor.getFirstName() + " " + professor.getSecondName(), Double.valueOf(mark), new SimpleDateFormat("yyyy-MM-dd").parse(date), comment));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
