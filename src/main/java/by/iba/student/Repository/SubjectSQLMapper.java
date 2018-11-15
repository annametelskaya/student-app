package by.iba.student.Repository;

import by.iba.student.common.Subject;

public class SubjectSQLMapper implements SQLMapper<Integer, Subject> {
    @Override
    public Integer getKey(Subject item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Subject item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }
}
