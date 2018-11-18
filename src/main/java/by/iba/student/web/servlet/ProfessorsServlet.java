package by.iba.student.web.servlet;

import by.iba.student.filter.ProfessorFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Professor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProfessorsServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String name = req.getParameter("sortByName");
        String surname = req.getParameter("sortBySurname");
        ProfessorFilter professorFilter = new ProfessorFilter();
        if (name != null)
            professorFilter.setFirstName(name);
        if (surname != null)
            professorFilter.setSecondName(surname);
        PrintWriter pw = resp.getWriter();
        List<Professor> professors = this.professorRepository.findAll(professorFilter);
        pw.print(mapper.writeValueAsString(professors));
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
        this.professorRepository.create(new Professor(jsonObject.getString("nameForm"),
                jsonObject.getString("surnameForm")));
    }

    @Override
    public void destroy() {

    }
}
