package by.iba.student.Repository;

import by.iba.student.common.Student;

public class StudentSQLMapper implements SQLMapper<Integer, Student> {
    @Override
    public Integer getKey(Student item) {
        return item.getId();
    }

    @Override
    public Integer setKey(Student item, int size) {
        int id = size+ 1;
        item.setId(id);
        return id;
    }
}
