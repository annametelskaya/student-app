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
        List<String> params = new ArrayList<>();
        List<Professor> professors = new ArrayList<>();
        String sql = "SELECT PROFESS_ID," +
                "FIRST_NAME," +
                "SECOND_NAME," +
                "AVG_MARK " +
                "FROM BEGANSS.PROFESS " +
                "WHERE " +
                SQLHelper.addLike(params, "FIRST_NAME", professorFilter.getFirstName(), " AND ") +
                SQLHelper.addLike(params, "SECOND_NAME", professorFilter.getSecondName(), " AND ") +
                "1=1";
        PreparedStatement statement = conn.prepareStatement(sql);
        SQLHelper.setParams(statement, params);
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
        String sql = "INSERT INTO BEGANSS.PROFESS(FIRST_NAME, SECOND_NAME, AVG_MARK) VALUES (?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getSecondName());
        statement.setDouble(3, item.getAvgMark());
        statement.executeUpdate();
        sql = "SELECT MAX(PROFESS_ID) AS 'PROFESS_ID' FROM BEGANSS.PROFESS;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("PROFESS_ID"));
        }
    }

    @Override
    public Professor findOne(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT PROFESS_ID, " +
                "FIRST_NAME, " +
                "SECOND_NAME, " +
                "AVG_MARK " +
                "FROM BEGANSS.PROFESS WHERE PROFESS_ID=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Professor professor = null;
        if (rs.next()) {
            professor = new Professor(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"));
            professor.setId(rs.getInt("PROFESS_ID"));
            professor.setAvgMark(rs.getString("AVG_MARK"));
        }
        return professor;
    }

    @Override
    public void delete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM BEGANSS.PROFESS "
                + "WHERE PROFESS_ID =?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }
}
