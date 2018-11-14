package by.iba.student.reader;

import by.iba.student.common.Marks;

public class MarkLineMapper implements LineMapper<Marks> {
    @Override
    public Marks mapLine(String line) {
        String[] data = line.split(";");
        Marks mark;
        if (data.length > 5) {
            mark = new Marks(data[1], data[2], data[3], data[4], data[5]);
        } else {
            mark = new Marks(data[1], data[2], data[3], data[4], "");
        }
        mark.setId(data[0]);
        return mark;
    }
}
