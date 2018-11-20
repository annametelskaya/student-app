package by.iba.student.repository;

import by.iba.student.common.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepository {

    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User logIn(String name, String pass) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT "
                    + "USERNAME, "
                    + "PASS, "
                    + "ROLE "
                    + "FROM BEGANSS.USER";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (name.equals(rs.getString("USERNAME")) && pass.equals(rs.getString("PASS"))) {
                    User user = new User(name, pass, rs.getString("ROLE"));
                    return user;
                }
            }
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
