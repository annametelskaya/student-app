package by.iba.student.web.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.Repository.GroupRepository;
import by.iba.student.Repository.StudentRepository;
import by.iba.student.common.Student;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (StudentRepository) sc.getAttribute("studentRepository");
        this.groupRepository = (GroupRepository) sc.getAttribute("groupRepository");
        this.studentRepository.setGroupRepository(this.groupRepository);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", this.studentRepository.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String groupId = req.getParameter("groupNumber");
        //Student st = new Student(firstName, secondName, groupId);
        Student st = new Student(firstName, secondName, groupRepository.findGroupById(groupId));
        this.studentRepository.create(st);
        //this.groupRepository.findGroupById(groupId).addStudent(st);
        groupRepository.findGroupById(groupId).addStudent();
        //Data.addNewStudent(groupNumber, new Student(firstName, secondName, groupNumber));
        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
