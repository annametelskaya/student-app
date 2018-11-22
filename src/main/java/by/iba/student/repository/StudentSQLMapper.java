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
        item.setId(""+id+"");
        return id;
    }

    @Override
    public List<Student> getData(Connection conn, StudentFilter studentFilter) throws SQLException {
        List<String> params = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        String sql = "SELECT ST.STUDENT_ID, " +
                "ST.FIRST_NAME, " +
                "ST.SECOND_NAME, " +
                "ST.AVG_MARK AS 'STUDENT_AVG', " +
                "ST.GROUP_NUMBER, " +
                "GR.AVG_MARK  AS 'GROUP_AVG' " +
                "FROM BEGANSS.STUDENT AS ST JOIN BEGANSS.GROUP AS GR ON ST.GROUP_NUMBER = GR.GROUP_NUMBER " +
                "WHERE " +
                SQLHelper.addLike(params, "ST.FIRST_NAME", studentFilter.getName(), " AND ") +
                SQLHelper.addLike(params, "ST.SECOND_NAME", studentFilter.getSurname(), " AND ") +
                SQLHelper.addLike(params, "GR.GROUP_NUMBER", studentFilter.getGroupNumber(), " AND ") +
                "1=1";
        PreparedStatement statement = conn.prepareStatement(sql);
        SQLHelper.setParams(statement, params);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Student student = fillStudent(rs);
            students.add(student);
        }
        return students;
    }

    @Override
    public void createData(Connection conn, Student item) throws SQLException {
        String sql = "INSERT INTO BEGANSS.STUDENT(FIRST_NAME, SECOND_NAME, AVG_MARK, GROUP_NUMBER) VALUES (?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getSecondName());
        statement.setDouble(3, item.getAvgMark());
        statement.setString(4, item.getGroupNumber());
        statement.executeUpdate();
        sql = "SELECT MAX(STUDENT_ID) AS 'STUDENT_ID' FROM BEGANSS.STUDENT;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getString("STUDENT_ID"));
        }
    }

    @Override
    public Student findOne(Connection conn, Integer id) throws SQLException {
        String sql = "SELECT ST.STUDENT_ID, " +
                "ST.FIRST_NAME, " +
                "ST.SECOND_NAME, " +
                "ST.AVG_MARK AS 'STUDENT_AVG', " +
                "ST.GROUP_NUMBER, " +
                "GR.AVG_MARK  AS 'GROUP_AVG' " +
                "FROM BEGANSS.STUDENT AS ST JOIN BEGANSS.GROUP AS GR ON ST.GROUP_NUMBER = GR.GROUP_NUMBER " +
                "WHERE STUDENT_ID=?;";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Student student = null;
        if (rs.next()) {
            student = fillStudent(rs);
        }
        return student;
    }

    @Override
    public void delete(Connection connection, Integer id) throws SQLException {
        String sql = "DELETE FROM BEGANSS.STUDENT "
                + "WHERE STUDENT_ID =?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }

    @Override
    public void update(Connection connection, Integer id, Student newItem) throws SQLException {

    }

    private Student fillStudent(ResultSet rs) throws SQLException {
        Student student = null;
        Group group = new Group(rs.getString("GROUP_NUMBER"));
        group.setAvg_mark(rs.getString("GROUP_AVG"));
        student = new Student(rs.getString("FIRST_NAME"), rs.getString("SECOND_NAME"),
                group);
        student.setAvgMark(rs.getString("STUDENT_AVG"));
        student.setId(rs.getString("STUDENT_ID"));
        return student;
    }
}
