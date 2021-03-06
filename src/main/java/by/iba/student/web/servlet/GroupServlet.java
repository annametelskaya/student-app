package by.iba.student.web.servlet;

import by.iba.student.filter.GroupFilter;
import by.iba.student.repository.Repository;
import by.iba.student.common.Group;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GroupServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    private Repository<String, Group, GroupFilter> groupRepository;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
        this.objectMapper = (ObjectMapper) sc.getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String sortByGroup = req.getParameter("sortByGroup");
        GroupFilter groupFilter = new GroupFilter();
        groupFilter.setGroupNumber(sortByGroup);
        req.setAttribute("items", this.groupRepository.findAll(groupFilter));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Group group = this.objectMapper.readValue(req.getInputStream(), Group.class);
        this.groupRepository.create(group);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        Group group = this.objectMapper.readValue(req.getInputStream(), Group.class);
        this.groupRepository.update(id, group );
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        this.groupRepository.delete(id);
    }
}