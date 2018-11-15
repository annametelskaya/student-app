package by.iba.student.Repository;

import by.iba.student.common.Group;

public class GroupSQLMapper implements SQLMapper<String, Group> {

    @Override
    public String getKey(Group item) {
        return item.getGroupNumber();
    }

    @Override
    public String setKey(Group item, int size) {
        return item.getGroupNumber();
    }
}
