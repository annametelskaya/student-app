package by.iba.student.repository;

import by.iba.student.common.*;
import by.iba.student.filter.MarksFilter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkSQLMapper implements SQLMapper<Integer, Marks, MarksFilter> {
    @Override
    public Integer getKey(Marks item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Marks item, int size) {
        int id = size + 1;
        item.setId(id);
        return id;
    }

    @Override
    public List<Marks> getData(Connection conn, MarksFilter marksFilter) throws SQLException {
        List<Marks> marks = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT * FROM BEGANSS.MARK;";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            Marks mark = null;
            try {
                GetItems gt = new GetItems();
                mark = new Marks(gt.getSubject(conn, rs.getString("STUDY_ID")),
                        gt.getStudent(conn, rs.getString("STUDENT_ID")),
                        gt.getProfessor(conn, rs.getString("PROFESS_ID")),
                        Double.valueOf(rs.getString("MARK")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATE")),
                        rs.getString("COMMENTS"));
                mark.setId(rs.getInt("MARK_ID"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            marks.add(mark);
        }
        return marks;
    }

    @Override
    public void createData(Connection conn, Marks item) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "INSERT INTO BEGANSS.MARK(STUDY_ID, STUDENT_ID, DATE, PROFESS_ID, MARK, COMMENTS) VALUES " +
                "('" + item.getSubjectId() + "','" + item.getStudentId() + "','" + item.getDate() + "','" + item.getProfessorId()
                + "','" + item.getMark() + "','" + item.getComment() + "');";
        statement.executeUpdate(sql);
        sql = "SELECT MAX(MARK_ID) AS 'MARK_ID' FROM BEGANSS.MARK";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("MARK_ID"));
        }
    }

    @Override
    public Marks findOne(Connection conn, Integer id) throws SQLException {
        Statement statement = conn.createStatement();
        String sql = "select * from BEGANSS.MARK where MARK_ID='" + id + "';";
        ResultSet pr = statement.executeQuery(sql);
        Marks mark = null;
        if (pr.next()) {
            GetItems gt = new GetItems();
            try {
                mark = new Marks(gt.getSubject(conn, pr.getString("STUDY_ID")), gt.getStudent(conn, pr.getString("STUDENT_ID")),
                        gt.getProfessor(conn, pr.getString("PROFESS_ID")), Double.valueOf(pr.getString("MARK")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(pr.getString("DATE")),
                        pr.getString("COMMENTS"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return mark;
    }

    @Override
    public List<Marks> findSort(Connection connection, String[] arg) {
        return null;
    }
}
