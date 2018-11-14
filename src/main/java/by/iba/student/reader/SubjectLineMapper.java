package by.iba.student.reader;

import by.iba.student.common.Subject;

public class SubjectLineMapper implements LineMapper<Subject> {
    @Override
    public Subject mapLine(String line) {
        String[] data = line.split(";");
        Subject subject = new Subject(data[1], data[2], data[3]);
        subject.setId(data[0]);
        return subject;
    }
}
