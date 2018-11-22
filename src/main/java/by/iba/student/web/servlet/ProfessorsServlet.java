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
import java.io.IOException;

public class ProfessorsServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Professor, ProfessorFilter> professorRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.professorRepository = (Repository<Integer, Professor, ProfessorFilter>) sc.getAttribute("professorRepository");
        this.objectMapper = (ObjectMapper) sc.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("sortByName");
        String surname = req.getParameter("sortBySurname");
        ProfessorFilter professorFilter = new ProfessorFilter();
        if (name != null)
            professorFilter.setFirstName(name);
        if (surname != null)
            professorFilter.setSecondName(surname);
        req.setAttribute("items", this.professorRepository.findAll(professorFilter));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Professor professor = this.objectMapper.readValue(req.getInputStream(), Professor.class);
        this.professorRepository.create(professor);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        this.professorRepository.delete(Integer.valueOf(id));
    }

    @Override
    public void destroy() {

    }
}
