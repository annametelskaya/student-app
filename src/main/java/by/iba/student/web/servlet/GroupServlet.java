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

    @Override
    public void init() {
        ServletContext sc = getServletContext();
        this.groupRepository = (Repository<String, Group, GroupFilter>) sc.getAttribute("groupRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        String sortByGroup = req.getParameter("sortByGroup");
        GroupFilter groupFilter = new GroupFilter();
        if (sortByGroup != null)
            groupFilter.setGroupNumber(sortByGroup);
        List<Group> groups = this.groupRepository.findAll(groupFilter);
        PrintWriter pw = resp.getWriter();
        pw.print(mapper.writeValueAsString(groups));
        pw.flush();
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jsonObject = new JSONObject(sb.toString());
        this.groupRepository.create(new Group(jsonObject.getString("groupNumber")));
    }
}