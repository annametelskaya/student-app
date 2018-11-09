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

import by.iba.student.common.Student;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;

    private final static List<Student> STUDENTS = new ArrayList<Student>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("students", STUDENTS);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
        dispatcher.forward(req, resp);

        //String name = req.getParameter("name");
				/*resp.setContentType("text/html");
				
				PrintWriter pw = resp.getWriter();
				pw.print("<form>"ui);
				pw.print("<input type=\"text\" name=\"name\">");
				pw.print("<input type=\"submit\">");
				pw.print("</form>");
				//pw.print("Hello, " + name);
				pw.close();*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        //System.out.println(String.format("First Name: %s, Second Name: %s", firstName, secondName));
        if (!firstName.equals("") && !secondName.equals("")) {
            STUDENTS.add(new Student(firstName, secondName));
        }
        doGet(req, resp);

    }


}
