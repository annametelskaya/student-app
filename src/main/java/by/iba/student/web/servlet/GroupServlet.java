package by.iba.student.web.servlet;

import by.iba.student.Repository.GroupRepository;
import by.iba.student.common.Group;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private GroupRepository groupRepository;


    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (GroupRepository) sc.getAttribute("groupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", this.groupRepository.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/groups.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("groupNumber");
//        String group = req.getParameter("groupNumber");
        this.groupRepository.create(new Group(number));
        doGet(req, resp);

    }

    @Override
    public void destroy() {

    }
}
