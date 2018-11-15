package by.iba.student.Repository;

import by.iba.student.common.Marks;

public class MarkSQLMapper implements SQLMapper<Integer, Marks> {
    @Override
    public Integer getKey(Marks item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Marks item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }
}
