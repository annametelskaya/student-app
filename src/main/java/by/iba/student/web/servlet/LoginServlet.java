package by.iba.student.web.servlet;

import by.iba.student.common.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -7452966718428268330L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        if ("admin".equals(name)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", new User(name, pass, "admin"));
            resp.sendRedirect("/groupsPage");
        } else {
            doGet(req, resp);
        }
    }
}
