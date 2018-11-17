package by.iba.student.web.servlet;

import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Group;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GroupServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<String, Group, GroupFilter> groupRepository;
    private ObjectMapper mapper;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
        this.mapper = (ObjectMapper) sc.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        String sortByGroup = req.getParameter("sortByGroup");
        GroupFilter groupFilter = new GroupFilter();
        if (sortByGroup != null)
            groupFilter.setGroupNumber(sortByGroup);
        List<Group> groups = this.groupRepository.findAll(groupFilter);
        PrintWriter pw = resp.getWriter();
        pw.print(mapper.writeValueAsString(groups));
        pw.close();
        //req.setAttribute("groups", this.groupRepository.findAll(groupFilter));
        //RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/groups.jsp");
        //dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("groupNumber");
//        String group = req.getParameter("groupNumber");
        this.groupRepository.create(new Group(number));
        doGet(req, resp);

    }
}