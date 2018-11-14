package by.iba.student.reader;

import by.iba.student.common.Group;

public class GroupLineMapper  implements LineMapper<Group> {
    @Override
    public Group mapLine(String line) {
        String[] data = line.split(";");
        Group group = new Group(data[1]);
        group.setId(data[0]);
        group.setNumber(data[2]);
        return group;
    }
}
