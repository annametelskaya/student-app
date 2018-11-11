package by.iba.student.web.servlet;

import by.iba.student.common.Data;
import by.iba.student.common.Marks;
import by.iba.student.common.Professor;
import by.iba.student.common.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MarksServelt extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("marks", Data.MARKS);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/marks.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] studentInfo = req.getParameter("selectedStudent").split(" ");
        String[] professorInfo = req.getParameter("selectedProfessor").split(" ");
        Student student = Data.findStudent(studentInfo[0], studentInfo[1]);
        Professor professor = Data.findProfessor(professorInfo[0], professorInfo[1], professorInfo[2]);
        String mark = req.getParameter("mark");
        String comment = req.getParameter("comment");
        Data.MARKS.add(new Marks(student, professor, mark, comment));
        doGet(req, resp);

    }


}
