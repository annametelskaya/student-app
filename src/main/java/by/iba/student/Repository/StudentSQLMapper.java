package by.iba.student.Repository;

import by.iba.student.common.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentSQLMapper implements SQLMapper<Integer, Student> {
    @Override
    public Integer getKey(Student item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Student item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Student> getData(Connection conn) throws SQLException {
        List<Student> students = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.STUDENT";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Student student = new Student(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"),
                    rs.getString("GROUP_NUMBER"));
            student.setAvgMark(rs.getString("AVG_MARK"));
            students.add(student);
        }
        return students;
    }
}
