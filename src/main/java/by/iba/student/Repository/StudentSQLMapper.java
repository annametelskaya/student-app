package by.iba.student.Repository;

import by.iba.student.common.Group;
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
        int id = size + 1;
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
            GetItems gt = new GetItems();
            Student student = new Student(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"),
                    gt.getGroup(conn, rs.getString("GROUP_NUMBER")));
            student.setAvgMark(rs.getString("AVG_MARK"));
            student.setId(rs.getInt("STUDENT_ID"));
            students.add(student);
        }
        return students;
    }

    @Override
    public void createData(Connection conn, Student item) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO BEGANSS.STUDENT(FIRST_NAME, SECOND_NAME, AVG_MARK, GROUP_NUMBER) VALUES ('" +
                item.getFirstName() + "','"
                + item.getSecondName() + "','"
                + item.getAvgMark() + "','"
                + item.getGroupNumber() + "');";
        statement.executeUpdate(sql);
        sql = "SELECT MAX(STUDENT_ID) AS 'STUDENT_ID' FROM BEGANSS.STUDENT";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("STUDENT_ID"));
        }
    }

    @Override
    public Student findOne(Connection conn, Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from BEGANSS.STUDENT where STUDENT_ID='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Student student = null;
        if (pr.next()) {
            GetItems gt = new GetItems();
            student = new Student(pr.getString("FIRST_NAME"), pr.getString("SECOND_NAME"),
                    gt.getGroup(conn, pr.getString("GROUP_NUMBER")));
            student.setId(pr.getInt("STUDENT_ID"));
            student.setAvgMark(pr.getString("AVG_MARK"));
        }
        return student;
    }
}
