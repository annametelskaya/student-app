package by.iba.student.web.servlet;

import by.iba.student.common.Group;
import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.Repository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<String, Group, GroupFilter> groupRepository;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", this.groupRepository.findAll(new GroupFilter()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addstudent.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {

    }
}
