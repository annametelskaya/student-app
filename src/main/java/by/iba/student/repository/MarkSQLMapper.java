package by.iba.student.repository;

import by.iba.student.common.*;
import by.iba.student.filter.MarksFilter;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkSQLMapper implements SQLMapper<Integer, Marks, MarksFilter> {
    private String sql = "SELECT M.MARK_ID, " +
            "M.STUDENT_ID, " +
            "M.STUDY_ID, " +
            "M.MARK, " +
            "M.DATE, " +
            "M.COMMENTS, " +
            "P.PROFESS_ID, " +
            "P.FIRST_NAME AS 'PROFESSOR_NAME', " +
            "P.SECOND_NAME AS 'PROFESSOR_SURNAME', " +
            "P.AVG_MARK AS 'PROFESS_AVG', " +
            "S.FIRST_NAME AS 'STUDENT_NAME', " +
            "S.SECOND_NAME AS 'STUDENT_SURNAME', " +
            "S.AVG_MARK AS 'STUDENT_AVG', " +
            "S.GROUP_NUMBER, " +
            "ST.NAME, " +
            "ST.HOURS, " +
            "ST.AVG_MARK AS 'STUDY_AVG', " +
            "G.AVG_MARK AS 'GROUP_AVG' " +
            "FROM BEGANSS.MARK M JOIN BEGANSS.PROFESS P ON M.PROFESS_ID = P.PROFESS_ID " +
            "JOIN BEGANSS.STUDENT S ON M.STUDENT_ID = S.STUDENT_ID " +
            "JOIN BEGANSS.STUDY ST ON M.STUDY_ID = ST.STUDY_ID " +
            "JOIN BEGANSS.GROUP G ON S.GROUP_NUMBER=G.GROUP_NUMBER " +
            "WHERE ";

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
        List<String> params = new ArrayList<>();
        String newsql = sql +
                SQLHelper.addLike(params, "CONCAT(P.FIRST_NAME,' ',P.SECOND_NAME)", marksFilter.getProfessor(), " AND ") +
                SQLHelper.addLike(params, "CONCAT(S.FIRST_NAME,' ',S.SECOND_NAME)", marksFilter.getStudent(), " AND ") +
                SQLHelper.addLike(params, "ST.NAME", marksFilter.getSubject(), " AND ") +
                SQLHelper.addLike(params, "M.MARK", marksFilter.getMark(), " AND ") +
                " 1=1 ";
        PreparedStatement statement = conn.prepareStatement(newsql);
        SQLHelper.setParams(statement, params);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Marks mark = fillMark(rs);
            marks.add(mark);
        }
        return marks;
    }

    @Override
    public void createData(Connection conn, Marks item) throws SQLException {
        String sql = "INSERT INTO BEGANSS.MARK(STUDY_ID, STUDENT_ID, PROFESS_ID, DATE, MARK, COMMENTS) VALUES (?,?,?,?,?,?);";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, item.getSubjectId());
        statement.setString(2, item.getStudentId());
        statement.setString(3, item.getProfessorId());
        statement.setString(4, item.getDate());
        statement.setDouble(5, item.getMark());
        statement.setString(6, item.getComment());
        statement.executeUpdate();
        sql = "SELECT MAX(MARK_ID) AS 'MARK_ID' FROM BEGANSS.MARK;";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            item.setId(rs.getInt("MARK_ID"));
        }
    }

    @Override
    public Marks findOne(Connection conn, Integer id) throws SQLException {
        List<String> params = new ArrayList<>();
        String newsql = sql + SQLHelper.addLike(params, "MARK_ID", id.toString(), " AND ");
        PreparedStatement statement = conn.prepareStatement(newsql);
        SQLHelper.setParams(statement, params);
        ResultSet rs = statement.executeQuery();
        Marks mark = null;
        if (rs.next()) {
            mark = fillMark(rs);
        }
        return mark;
    }

    private Marks fillMark(ResultSet rs) throws SQLException {
        Marks mark = null;
        try {
            Professor professor = new Professor(rs.getString("PROFESSOR_NAME"), rs.getString("PROFESSOR_SURNAME"));
            professor.setId(rs.getInt("PROFESS_ID"));
            professor.setAvgMark(rs.getString("PROFESS_AVG"));
            Subject subject = new Subject(rs.getString("NAME"), rs.getInt("HOURS"), professor);
            subject.setId(rs.getInt("STUDY_ID"));
            subject.setAvgMark(rs.getString("STUDY_AVG"));
            Group group = new Group(rs.getString("GROUP_NUMBER"));
            group.setAvg_mark(rs.getString("GROUP_AVG"));
            Student student = new Student(rs.getString("STUDENT_NAME"), rs.getString("STUDENT_SURNAME"), group);
            student.setId(rs.getInt("STUDENT_ID"));
            student.setAvgMark(rs.getString("STUDENT_AVG"));
            mark = new Marks(subject, student, Double.valueOf(rs.getString("MARK")),
                    new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATE")),
                    rs.getString("COMMENTS"));
            mark.setId(rs.getInt("MARK_ID"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mark;
    }
}
