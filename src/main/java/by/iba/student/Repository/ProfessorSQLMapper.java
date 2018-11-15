package by.iba.student.Repository;

import by.iba.student.common.Professor;

public class ProfessorSQLMapper implements SQLMapper<Integer, Professor> {
    @Override
    public Integer getKey(Professor item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Professor item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }
}
