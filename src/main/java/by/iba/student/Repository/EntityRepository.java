package by.iba.student.Repository;

import java.util.List;

public interface EntityRepository<C, T> {
    public List<T> findAll();

    public T findById(C id);

    public void create(T item);

    public void delete(String id);

    public T update(T item);
}
