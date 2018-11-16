package by.iba.student.repository;

import by.iba.student.common.Group;
import by.iba.student.common.Student;
import by.iba.student.filter.StudentFilter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSQLMapper implements SQLMapper<Integer, Student, StudentFilter> {
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
    public List<Student> getData(Connection conn, StudentFilter studentFilter) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT ST.STUDENT_ID,ST.FIRST_NAME,ST.SECOND_NAME,ST.AVG_MARK AS 'STUDENT_AVG',ST.GROUP_NUMBER, GR.AVG_MARK  AS 'GROUP_AVG'" +
                " FROM BEGANSS.STUDENT AS ST JOIN BEGANSS.GROUP AS GR ON ST.GROUP_NUMBER = GR.GROUP_NUMBER " +
                "WHERE ST.FIRST_NAME LIKE ? " +
                "AND ST.SECOND_NAME LIKE  ? AND GR.GROUP_NUMBER LIKE ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, studentFilter.getName() + "%");
        statement.setString(2, studentFilter.getSurname() + "%");
        statement.setString(3, studentFilter.getGroupNumber() + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            GetItems gt = new GetItems();
            Student student = new Student(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"),
                    gt.getGroup(conn, rs.getString("GROUP_NUMBER")));
            student.setAvgMark(rs.getString("STUDENT_AVG"));
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

    @Override
    public List<Student> findSort(Connection connection, String[] arg) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT ST.STUDENT_ID,ST.FIRST_NAME,ST.SECOND_NAME,ST.AVG_MARK AS 'STUDENT_AVG',ST.GROUP_NUMBER, GR.AVG_MARK  AS 'GROUP_AVG'" +
                " FROM BEGANSS.STUDENT AS ST JOIN BEGANSS.GROUP AS GR ON ST.GROUP_NUMBER = GR.GROUP_NUMBER " +
                "WHERE ST.FIRST_NAME LIKE ? " +
                "AND ST.SECOND_NAME LIKE  ? AND GR.GROUP_NUMBER LIKE ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, arg[0] + "%");
        statement.setString(2, arg[1] + "%");
        statement.setString(3, arg[2] + "%");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Group group = new Group(rs.getString("GROUP_NUMBER"));
            group.setAvg_mark(rs.getString("GROUP_AVG"));
            Student student = new Student(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"),
                    group);
            student.setAvgMark(rs.getString("STUDENT_AVG"));
            student.setId(rs.getInt("STUDENT_ID"));
            students.add(student);
        }
        return students;
    }
}
