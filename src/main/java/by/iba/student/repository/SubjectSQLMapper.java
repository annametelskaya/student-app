package by.iba.student.repository;

import by.iba.student.common.Professor;
import by.iba.student.common.Subject;
import by.iba.student.filter.SubjectFilter;

import java.sql.*;
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
        String sql = "SELECT ST.STUDY_ID, " +
                "ST.NAME, " +
                "ST.HOURS, " +
                "ST.AVG_MARK AS 'STUDY_AVG', " +
                "PR.PROFESS_ID, " +
                "PR.FIRST_NAME, " +
                "PR.SECOND_NAME, " +
                "PR.AVG_MARK AS 'PROFESS_AVG' " +
                "FROM BEGANSS.STUDY ST JOIN BEGANSS.PROFESS PR ON ST.PROFESS_ID = PR.PROFESS_ID " +
                "WHERE ST.NAME LIKE ? AND ST.HOURS LIKE ? AND CONCAT(PR.FIRST_NAME,' ',PR.SECOND_NAME) LIKE ?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, subjectFilter.getName() + "%");
        statement.setString(2, subjectFilter.getHours() + "%");
        statement.setString(3, subjectFilter.getProfessor() + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Subject subject = fillSubject(rs);
            subjects.add(subject);
        }
        return subjects;
    }

    @Override
    public void createData(Connection conn, Subject item) throws SQLException {
        String sql = "INSERT INTO BEGANSS.STUDY(NAME, HOURS, PROFESS_ID, AVG_MARK) VALUES (?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, item.getName());
        statement.setInt(2, item.getHours());
        statement.setString(3, item.getProfessorId());
        statement.setDouble(4, item.getAvgMark());
        statement.executeUpdate();
        sql = "SELECT MAX(STUDY_ID) AS 'STUDY_ID' FROM BEGANSS.STUDY;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("STUDY_ID"));
        }
    }

    @Override
    public Subject findOne(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT ST.STUDY_ID, " +
                "ST.NAME, " +
                "ST.HOURS, " +
                "ST.AVG_MARK AS 'STUDY_AVG', " +
                "PR.PROFESS_ID, " +
                "PR.FIRST_NAME, " +
                "PR.SECOND_NAME, " +
                "PR.AVG_MARK AS 'PROFESS_AVG' " +
                "FROM BEGANSS.STUDY ST JOIN BEGANSS.PROFESS PR ON ST.PROFESS_ID = PR.PROFESS_ID " +
                "WHERE STUDY_ID= ?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Subject subject = null;
        if (rs.next()) {
            subject = fillSubject(rs);
        }
        return subject;
    }

    private Subject fillSubject(ResultSet rs) throws SQLException {
        Subject subject = null;
        Professor professor = new Professor(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"));
        professor.setId(rs.getInt("PROFESS_ID"));
        professor.setAvgMark(rs.getString("PROFESS_AVG"));
        subject = new Subject(rs.getString("NAME"),
                Integer.valueOf(rs.getString("HOURS")), professor);
        subject.setAvgMark(rs.getString("STUDY_AVG"));
        subject.setId(rs.getInt("STUDY_ID"));
        return subject;
    }
}
