package by.iba.student.web.servlet;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.Repository.Repository;
import by.iba.student.common.Group;
import by.iba.student.common.Student;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<Integer, Student> studentRepository;
    private Repository<String, Group> groupRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.studentRepository = (Repository<Integer, Student>) sc.getAttribute("studentRepository");
        this.groupRepository = (Repository<String, Group>) sc.getAttribute("groupRepository");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortByName = req.getParameter("sortByName");
        String sortBySurname = req.getParameter("sortBySurname");
        String sortByGroup = req.getParameter("sortByGroup");
        if (sortByName!=null || sortByGroup!=null || sortBySurname!=null) {
            if(sortByGroup==null){
                sortByGroup="";
            }
            if (sortByName==null){
                sortByName="";
            }
            if(sortBySurname==null){
                sortBySurname="";
            }
            req.setAttribute("students", this.studentRepository.findBySort(new String[]{sortByName, sortBySurname, sortByGroup}));
        } else {
            req.setAttribute("students", this.studentRepository.findAll());
        }
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
