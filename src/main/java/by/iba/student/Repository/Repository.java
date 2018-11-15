package by.iba.student.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Repository<C, T> implements EntityRepository<C, T> {
    public final Map<C, T> items = new LinkedHashMap<>();
    public SQLMapper<C, T> mapper;

    public Repository(SQLMapper<C, T> mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<T> findAll() {
        List<T> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BEGANSS", "root", "30inutez")) {
            items.addAll(mapper.getData(conn));
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        return items;
    }

    @Override
    public T findById(C id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BEGANSS", "root", "30inutez")) {
            return mapper.findOne(conn, id);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void create(T item) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BEGANSS", "root", "30inutez")) {
            mapper.createData(conn, item);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        C id = mapper.setKey(item, items.size());
        items.put(id, item);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public T update(T item) {
        return null;
    }
}
