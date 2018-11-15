package by.iba.student.Repository;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import util.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GroupSQLMapper implements SQLMapper<String, Group> {

    @Override
    public String getKey(Group item) {
        return item.getGroupNumber();
    }

    @Override
    public String setKey(Group item, int size) {
        return item.getGroupNumber();
    }

    @Override
    public List<Group> getData(Connection conn) throws SQLException {
        List<Group> groups = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.GROUP";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Group group = new Group(rs.getString("GROUP_NUMBER"));
            group.setAvg_mark(rs.getString("AVG_MARK"));
            groups.add(group);
        }
        return groups;
    }
}
