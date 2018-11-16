package by.iba.student.web.servlet;

import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.Repository;
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
    private Repository<String, Group, GroupFilter> groupRepository;


    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sortByGroup = req.getParameter("sortByGroup");
        GroupFilter groupFilter = new GroupFilter();
        if (sortByGroup != null)
            groupFilter.setGroupNumber(sortByGroup);
        req.setAttribute("groups", this.groupRepository.findAll(groupFilter));
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
