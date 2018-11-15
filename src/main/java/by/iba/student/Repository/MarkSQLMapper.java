package by.iba.student.Repository;

import by.iba.student.common.Marks;
import by.iba.student.common.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MarkSQLMapper implements SQLMapper<Integer, Marks> {
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
    public List<Marks> getData(Connection conn) throws SQLException {
        List<Marks> marks = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = "SELECT "
                + "* "
                + "FROM BEGANSS.MARK;";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            sql = "select FIRST_NAME, SECOND_NAME " +
                    "from BEGANSS.STUDENT where STUDENT_ID=" +
                    rs.getString("STUDENT_ID") + ";";
            ResultSet st = statement.executeQuery(sql);
            String student = st.getString("FIRST_NAME") + " " + st.getString("SECOND_NAME");
            sql = "select NAME " +
                    "from BEGANSS.STUDY where STUDENT_ID=" +
                    rs.getString("STUDY_ID") + ";";
            ResultSet sb = statement.executeQuery(sql);
            String subject = sb.getString("NAME");
            sql = "select FIRST_NAME, SECOND_NAME " +
                    "from BEGANSS.PROFESS where PROFESS_ID=" +
                    rs.getString("PROFESS_ID") + ";";
            ResultSet pr = statement.executeQuery(sql);
            String profess = pr.getString("FIRST_NAME") + " " + pr.getString("SECOND_NAME");
            Marks mark = null;
            try {
                mark = new Marks(subject, student, profess, Double.valueOf(rs.getString("MARK")),
                        new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("DATE")),
                        rs.getString("COMMENTS"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            marks.add(mark);
        }
        return marks;
    }
}
