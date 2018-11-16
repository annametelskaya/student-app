package by.iba.student.repository;

import by.iba.student.common.Group;
import by.iba.student.filter.GroupFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupSQLMapper implements SQLMapper<String, Group, GroupFilter> {

    @Override
    public String getKey(Group item) {
        return item.getGroupNumber();
    }

    @Override
    public String setKey(Group item, int size) {
        return item.getGroupNumber();
    }

    @Override
    public List<Group> getData(Connection conn, GroupFilter groupFilter) throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT " +
                "GROUP_NUMBER, " +
                "AVG_MARK " +
                "FROM BEGANSS.GROUP " +
                "WHERE GROUP_NUMBER LIKE ? AND AVG_MARK LIKE ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, groupFilter.getGroupNumber() + "%");
        statement.setString(2, groupFilter.getAvg_mark() + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Group group = new Group(rs.getString("GROUP_NUMBER"));
            group.setAvg_mark(rs.getString("AVG_MARK"));
            groups.add(group);
        }
        return groups;
    }

    @Override
    public void createData(Connection conn, Group item) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO BEGANSS.GROUP(GROUP_NUMBER, AVG_MARK) VALUES" +
                " ('" + item.getGroupNumber() + "','" + item.getAvg_mark() + "');";
        statement.executeUpdate(sql);
    }

    @Override
    public Group findOne(Connection conn, String id) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from BEGANSS.GROUP where GROUP_NUMBER='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Group group = null;
        if (pr.next()) {
            group = new Group(pr.getString("GROUP_NUMBER"));
            group.setAvg_mark(pr.getString("AVG_MARK"));
        }
        return group;
    }

    @Override
    public List<Group> findSort(Connection connection, String[] arg) {
        return null;
    }

}
