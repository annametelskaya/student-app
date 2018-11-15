package by.iba.student.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface SQLMapper<C, T> {
    public C getKey(T item);

    public C setKey(T item, int size);

    public List<T> getData(Connection conn) throws SQLException;
}
