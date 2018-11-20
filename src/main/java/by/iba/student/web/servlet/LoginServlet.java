package by.iba.student.web.servlet;

import by.iba.student.common.Group;
import by.iba.student.common.User;
import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.Repository;
import by.iba.student.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -7452966718428268330L;

    UserRepository userRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.userRepository = (UserRepository) sc.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        User user = this.userRepository.logIn(name, pass);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/groupsPage");
        } else {
            doGet(req, resp);
        }
    }
}
