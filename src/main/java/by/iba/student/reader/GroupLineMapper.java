package by.iba.student.reader;

import by.iba.student.common.Group;

public class GroupLineMapper  implements LineMapper<Group> {
    @Override
    public Group mapLine(String line) {
        String[] data = line.split(";");
        Group group = new Group(data[0]);
        group.setAvg_mark(data[1]);
        return group;
    }
}
