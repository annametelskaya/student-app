package by.iba.student.repository;

import java.util.List;

public interface EntityRepository<C, T, F> {
    public List<T> findAll(F item);

    public T findById(C id);

    public void create(T item);

    public void delete(C id);

    public void update(C id, T newItem);
}
