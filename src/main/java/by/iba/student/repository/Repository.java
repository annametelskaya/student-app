package by.iba.student.repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Repository<C, T, F> implements EntityRepository<C, T, F> {
    SQLMapper<C, T, F> mapper;
    DataSource dataSource;

    public Repository(SQLMapper<C, T, F> mapper, DataSource dataSource) {
        this.mapper = mapper;
        this.dataSource = dataSource;
    }


    @Override
    public List<T> findAll(F item) {
        List<T> items = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            items.addAll(mapper.getData(conn, item));
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        return items;
    }

    @Override
    public T findById(C id) {
        try (Connection conn = dataSource.getConnection()) {
            return mapper.findOne(conn, id);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void create(T item) {
        try (Connection conn = dataSource.getConnection()) {
            mapper.createData(conn, item);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public T update(T item) {
        return null;
    }

    @Override
    public List<T> findBySort(String[] arg) {
        List<T> items = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/BEGANSS", "root", "30inutez")) {
            items.addAll(mapper.findSort(conn, arg));
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
        return items;
    }
}
