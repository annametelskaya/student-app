package by.iba.student.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SQLMapper<C, T, F> {
    public C getKey(T item);

    public C setKey(T item, int size);

    public List<T> getData(Connection conn, F item) throws SQLException;

    public void createData(Connection conn, T item) throws SQLException;

    public T findOne(Connection conn, C id) throws SQLException;

    public List<T> findSort(Connection connection, String[] arg) throws SQLException;
}
