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
        ObjectMapper mapper = new ObjectMapper();
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
        List<Subject> subjects = this.subjectRepository.findAll(subjectFilter);
        PrintWriter pw = resp.getWriter();
        pw.print(mapper.writeValueAsString(subjects));
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
        Professor professor = this.professorRepository.findById(Integer.valueOf(jsonObject.getString("selectedProfessor")));
        this.subjectRepository.create(new Subject(jsonObject.getString("subjectName"),
                Integer.valueOf(jsonObject.getString("hours")), professor));
    }

    @Override
    public void destroy() {

    }
}
