package by.iba.student.Repository;

public interface SQLMapper<C, T> {
    public C getKey(T item);

    public C setKey(T item, int size);
}
