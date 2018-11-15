package by.iba.student.reader;

import by.iba.student.common.Student;

public class StudentLineMapper implements LineMapper<Student> {


    @Override
    public Student mapLine(String line) {
        String[] data = line.split(";");
        Student student = new Student(data[1], data[2], data[3]);
        student.setId(Integer.valueOf(data[0]));
        return student;
    }
}
