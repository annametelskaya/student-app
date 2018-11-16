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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        req.setAttribute("students", this.studentRepository.findAll(studentFilter));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String groupNumber = req.getParameter("groupNumber");
        Student st = new Student(firstName, secondName, groupRepository.findById(groupNumber));
        this.studentRepository.create(st);
        doGet(req, resp);
    }

    @Override
    public void destroy() {

    }
}
