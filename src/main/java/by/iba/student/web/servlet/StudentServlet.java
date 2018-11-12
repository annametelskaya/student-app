package by.iba.student.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.iba.student.Repository.StudentRep;
import by.iba.student.common.Data;
import by.iba.student.common.Student;
import by.iba.student.reader.StudentReader;
import by.iba.student.writer.StudentWriter;

public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private final String path = "/home/anna/uni/git/student-app/src/main/resources/students.csv";
    private StudentRep rep;

    @Override
    public void init() {
        try {
            List<Student> students = new StudentReader(path).reader();
            this.rep = new StudentRep(students);
        } catch (IOException e) {
            try {
                throw new ServletException(e);
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", rep.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String groupNumber = req.getParameter("groupNumber");
        rep.create(new Student(firstName, secondName, groupNumber));
        //Data.STUDENTS.add(new Student(firstName, secondName, groupNumber));
        //Data.addNewStudent(groupNumber, new Student(firstName, secondName, groupNumber));
        doGet(req, resp);

    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("students", Data.STUDENTS);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/students.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String firstName = req.getParameter("firstName");
//        String secondName = req.getParameter("secondName");
//        String groupNumber = req.getParameter("groupNumber");
//        Data.STUDENTS.add(new Student(firstName, secondName, groupNumber));
//        Data.addNewStudent(groupNumber, new Student(firstName, secondName, groupNumber));
//        doGet(req, resp);
//
//    }

    @Override
    public void destroy() {
        try {
            List<Student> students = rep.findAll();
            new StudentWriter(path).write(students);
        } catch (IOException e) {
            System.out.println("can't save file");
        }
    }
}
