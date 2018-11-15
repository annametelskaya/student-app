package by.iba.student.Repository;

import by.iba.student.common.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectSQLMapper implements SQLMapper<Integer, Subject> {
    @Override
    public Integer getKey(Subject item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Subject item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Subject> getData(Connection conn) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.STUDY;";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            sql = "select FIRST_NAME, SECOND_NAME " +
                    "from BEGANSS.PROFESS where PROFESS_ID=" +
                    rs.getString("PROFESS_ID") + ";";
            ResultSet pr = statement.executeQuery(sql);
            String profess = pr.getString("FIRST_NAME") + " " + pr.getString("SECOND_NAME");
            Subject subject= new Subject(rs.getString("NAME"),Integer.valueOf(rs.getString("HOURS")),profess);
            subjects.add(subject);
        }
        return subjects;
    }
}
