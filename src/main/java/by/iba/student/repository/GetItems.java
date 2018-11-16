package by.iba.student.repository;

import by.iba.student.common.Group;
import by.iba.student.common.Professor;
import by.iba.student.common.Student;
import by.iba.student.common.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetItems {
    public Professor getProfessor(Connection connection, String id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from BEGANSS.PROFESS where PROFESS_ID='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Professor professor = null;
        if (pr.next()) {
            professor = new Professor(pr.getString("FIRST_NAME"), pr.getString("SECOND_NAME"));
            professor.setId(pr.getInt("PROFESS_ID"));
            professor.setAvgMark(pr.getString("AVG_MARK"));
        }
        return professor;
    }

    public Student getStudent(Connection connection, String id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from BEGANSS.STUDENT where STUDENT_ID='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Student student = null;
        if (pr.next()) {
            student = new Student(pr.getString("FIRST_NAME"), pr.getString("SECOND_NAME"),
                    getGroup(connection, pr.getString("GROUP_NUMBER")));
            student.setId(pr.getInt("STUDENT_ID"));
            student.setAvgMark(pr.getString("AVG_MARK"));
        }
        return student;
    }

    public Group getGroup(Connection connection, String id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * from BEGANSS.GROUP where GROUP_NUMBER='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Group group = null;
        if (pr.next()) {
            group = new Group(pr.getString("GROUP_NUMBER"));
            group.setAvg_mark(pr.getString("AVG_MARK"));
        }
        return group;
    }

    public Subject getSubject(Connection connection, String id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select * " +
                "from BEGANSS.STUDY where STUDY_ID='" +
                id + "';";
        Subject subject = null;
        ResultSet sb = statement.executeQuery(sql);
        if (sb.next()) {
            subject = new Subject(sb.getString("NAME"), sb.getInt("HOURS"),
                    getProfessor(connection, sb.getString("PROFESS_ID")));

            subject.setAvgMark(sb.getString("AVG_MARK"));
            subject.setId(sb.getInt("STUDY_ID"));
        }
        return subject;
    }
}
