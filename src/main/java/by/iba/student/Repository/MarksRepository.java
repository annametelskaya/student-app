package by.iba.student.Repository;

import by.iba.student.common.Marks;

import java.util.*;

public class MarksRepository {
    public final Map<String, Marks> marks = new LinkedHashMap<>();


    public MarksRepository(List<Marks> marks) {
        if (marks != null) {
            for (Marks mark : marks) {
                this.marks.put(mark.getId(), mark);
            }
        }
    }

    public Marks findMarkById(String id) {
        for (Marks mark : marks.values()) {
            if (mark.getId().equals(id)) {
                return mark;
            }
        }
        return null;
    }

    public List<Marks> findAll() {
        return new ArrayList<>(marks.values());
    }

    public void create(Marks mark) {
        String id = UUID.randomUUID().toString();
        mark.setId(id);
        marks.put(id, mark);
        //return student;
    }

    public void delete(String id) {
        marks.remove(id);
    }

    public Marks update(Marks mark) {
        marks.put(mark.getId(), mark);
        return mark;
    }
}