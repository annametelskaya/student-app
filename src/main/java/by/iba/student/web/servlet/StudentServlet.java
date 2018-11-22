package by.iba.student.web.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.filter.GroupFilter;
import by.iba.student.filter.StudentFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Group;
import by.iba.student.common.Student;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<String, Group, GroupFilter> groupRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (Repository<Integer, Student, StudentFilter>) sc.getAttribute("studentRepository");
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
        this.objectMapper = (ObjectMapper) sc.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sortByName = req.getParameter("sortByName");
        String sortBySurname = req.getParameter("sortBySurname");
        String sortByGroup = req.getParameter("sortByGroup");
        StudentFilter studentFilter = new StudentFilter();
        if (sortByName != null)
            studentFilter.setName(sortByName);
        if (sortBySurname != null)
            studentFilter.setSurname(sortBySurname);
        if (sortByGroup != null)
            studentFilter.setGroupNumber(sortByGroup);
        req.setAttribute("items", this.studentRepository.findAll(studentFilter));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Student student = this.objectMapper.readValue(req.getReader(), Student.class);
        this.studentRepository.create(student);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        this.studentRepository.delete(Integer.valueOf(id));
    }


    @Override
    public void destroy() {

    }
}
