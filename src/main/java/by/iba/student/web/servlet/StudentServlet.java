package by.iba.student.web.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.filter.GroupFilter;
import by.iba.student.filter.StudentFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Group;
import by.iba.student.common.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student, StudentFilter> studentRepository;
    private Repository<String, Group, GroupFilter> groupRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (Repository<Integer, Student, StudentFilter>) sc.getAttribute("studentRepository");
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
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
        List<Student> students = this.studentRepository.findAll(studentFilter);
        PrintWriter pw = resp.getWriter();
        pw.print(mapper.writeValueAsString(students));
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
        Student st = new Student(jsonObject.getString("firstName"),
                jsonObject.getString("secondName"),
                groupRepository.findById(jsonObject.getString("groupNumber")));
        this.studentRepository.create(st);
    }

    @Override
    public void destroy() {

    }
}
