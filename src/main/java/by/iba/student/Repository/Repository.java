package by.iba.student.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Repository<C, T> implements EntityRepository<C, T> {
    public final Map<C, T> items = new LinkedHashMap<>();
    public SQLMapper<C, T> mapper;

    public Repository(SQLMapper<C, T> mapper, List<T> items) {
        this.mapper = mapper;
        if (items != null) {
            for (T item : items) {
                this.items.put(mapper.getKey(item), item);
            }
        }
    }


    @Override
    public List<T> findAll() {
        return new ArrayList<T>(items.values());
    }

    @Override
    public T findById(C id) {
        for (T item : items.values()) {
            if (mapper.getKey(item) == id) return item;
        }
        return null;
    }

    @Override
    public void create(T item) {
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
