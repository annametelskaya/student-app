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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MarksServelt extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private Repository<Integer, Marks, MarksFilter> marksRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.marksRepository = (Repository<Integer, Marks, MarksFilter>) sc.getAttribute("marksRepository");
        this.studentRepository = (Repository<Integer, Student, StudentFilter>) sc.getAttribute("studentRepository");
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
        this.subjectRepository = (Repository<Integer, Subject, SubjectFilter>) sc.getAttribute("subjectRepository");
        this.objectMapper = (ObjectMapper) sc.getAttribute("objectMapper");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sortBySubject = req.getParameter("sortBySubject");
        String sortByStudent = req.getParameter("sortByStudent");
        String sortByProfessor = req.getParameter("sortByProfessor");
        String sortByMark = req.getParameter("sortByMark");
        MarksFilter marksFilter = new MarksFilter();
        if (sortBySubject != null)
            marksFilter.setSubject(sortBySubject);
        if (sortByStudent != null)
            marksFilter.setStudent(sortByStudent);
        if (sortByProfessor != null)
            marksFilter.setProfessor(sortByProfessor);
        if (sortByMark != null)
            marksFilter.setMark(sortByMark);
        req.setAttribute("items", this.marksRepository.findAll(marksFilter));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Marks marks = this.objectMapper.readValue(req.getInputStream(), Marks.class);
        System.out.println(marks + " \n" + marks.getProfessor());
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        StringBuilder sb = new StringBuilder();
//        BufferedReader br = req.getReader();
//        String str;
//        while ((str = br.readLine()) != null) {
//            sb.append(str);
//        }
//        req.setAttribute("strPost", sb);
//     //   String str = this.objectMapper.readValue(req.getInputStream(), String.class);
//        System.out.println(sb.toString());
        this.marksRepository.create(marks);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        this.marksRepository.delete(Integer.valueOf(id));
    }

    @Override
    public void destroy() {

    }
}
