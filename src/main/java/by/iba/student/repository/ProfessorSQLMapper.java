package by.iba.student.repository;

import by.iba.student.common.Professor;
import by.iba.student.filter.ProfessorFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorSQLMapper implements SQLMapper<Integer, Professor, ProfessorFilter> {
    @Override
    public Integer getKey(Professor item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Professor item, int size) {
        int id = size + 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Professor> getData(Connection conn, ProfessorFilter professorFilter) throws SQLException {
        List<Professor> professors = new ArrayList<>();
        String sql = "SELECT PROFESS_ID," +
                "FIRST_NAME," +
                "SECOND_NAME," +
                "AVG_MARK " +
                "FROM BEGANSS.PROFESS " +
                "WHERE FIRST_NAME LIKE ? AND SECOND_NAME LIKE ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, professorFilter.getFirstName() + "%");
        statement.setString(2, professorFilter.getSecondName() + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Professor professor = new Professor(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"));
            professor.setId(rs.getInt("PROFESS_ID"));
            professor.setAvgMark(rs.getString("AVG_MARK"));
            professors.add(professor);
        }
        return professors;
    }

    @Override
    public void createData(Connection conn, Professor item) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO BEGANSS.PROFESS(FIRST_NAME, SECOND_NAME, AVG_MARK) VALUES" +
                " ('" + item.getFirstName() + "','" + item.getSecondName() + "','" + item.getAvgMark() + "');";
        statement.executeUpdate(sql);
        sql = "SELECT MAX(PROFESS_ID) AS 'PROFESS_ID' FROM BEGANSS.PROFESS";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("PROFESS_ID"));
        }
    }

    @Override
    public Professor findOne(Connection conn, Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from BEGANSS.PROFESS where PROFESS_ID=" + id + ";";
        ResultSet pr = statement.executeQuery(sql);
        Professor professor = null;
        if (pr.next()) {
            professor = new Professor(pr.getString("FIRST_NAME"), pr.getString("SECOND_NAME"));
            professor.setId(id);
            professor.setAvgMark(pr.getString("AVG_MARK"));
        }
        return professor;
    }

    @Override
    public List<Professor> findSort(Connection connection, String[] arg) {
        return null;
    }
}
