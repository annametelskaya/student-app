package by.iba.student.repository;

import by.iba.student.common.Subject;
import by.iba.student.filter.SubjectFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectSQLMapper implements SQLMapper<Integer, Subject, SubjectFilter> {
    @Override
    public Integer getKey(Subject item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Subject item, int size) {
        int id = size + 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Subject> getData(Connection conn, SubjectFilter subjectFilter) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.STUDY;";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            GetItems gt = new GetItems();
            Subject subject = new Subject(rs.getString("NAME"),
                    Integer.valueOf(rs.getString("HOURS")), gt.getProfessor(conn, rs.getString("PROFESS_ID")));
            subject.setId(rs.getInt("STUDY_ID"));
            subjects.add(subject);
        }
        return subjects;
    }

    @Override
    public void createData(Connection conn, Subject item) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO BEGANSS.STUDY(NAME, HOURS, PROFESS_ID, AVG_MARK) VALUES ('" +
                item.getName() + "','"
                + item.getHours() + "','"
                + item.getProfessorId() + "','"
                + item.getAvgMark() + "');";
        statement.executeUpdate(sql);
        sql = "SELECT MAX(STUDY_ID) AS 'STUDY_ID' FROM BEGANSS.STUDY";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("STUDY_ID"));
        }
    }

    @Override
    public Subject findOne(Connection conn, Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * " +
                "from BEGANSS.STUDY where STUDY_ID='" +
                id + "';";
        Subject subject = null;
        ResultSet sb = statement.executeQuery(sql);
        if (sb.next()) {
            GetItems gt = new GetItems();
            subject = new Subject(sb.getString("NAME"), sb.getInt("HOURS"),
                    gt.getProfessor(conn, sb.getString("PROFESS_ID")));

            subject.setAvgMark(sb.getString("AVG_MARK"));
            subject.setId(sb.getInt("STUDY_ID"));
        }
        return subject;
    }

    @Override
    public List<Subject> findSort(Connection connection, String[] arg) {
        return null;
    }


}
