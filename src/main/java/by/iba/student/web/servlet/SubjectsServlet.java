package by.iba.student.web.servlet;

import by.iba.student.filter.ProfessorFilter;
import by.iba.student.filter.SubjectFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.*;
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
import java.util.List;

public class SubjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;

    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private Repository<Integer, Subject, SubjectFilter> subjectRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.subjectRepository = (Repository<Integer, Subject, SubjectFilter>) sc.getAttribute("subjectRepository");
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String professor = req.getParameter("sortByProf");
        String name = req.getParameter("sortByName");
        String hours = req.getParameter("sortByHours");
        SubjectFilter subjectFilter = new SubjectFilter();
        if (professor != null)
            subjectFilter.setProfessor(professor);
        if (name != null)
            subjectFilter.setName(name);
        if (hours != null)
            subjectFilter.setHours(hours);
        req.setAttribute("items", this.subjectRepository.findAll(subjectFilter));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = (StringBuilder) req.getAttribute("strPost");
        JSONObject jsonObject = new JSONObject(sb.toString());
        Professor professor = this.professorRepository.findById(Integer.valueOf(jsonObject.getString("selectedProfessor")));
        this.subjectRepository.create(new Subject(jsonObject.getString("subjectName"),
                Integer.valueOf(jsonObject.getString("hours")), professor));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        this.subjectRepository.delete(Integer.valueOf(id));
    }


    @Override
    public void destroy() {

    }
}
