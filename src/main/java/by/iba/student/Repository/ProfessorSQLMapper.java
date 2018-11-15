package by.iba.student.Repository;

import by.iba.student.common.Professor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfessorSQLMapper implements SQLMapper<Integer, Professor> {
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
    public List<Professor> getData(Connection conn) throws SQLException {
        List<Professor> professors = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.PROFESS";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Professor professor = new Professor(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"));
            professor.setAvgMark(rs.getString("AVG_MARK"));
            professors.add(professor);
        }
        return professors;
    }
}
