package by.iba.student.reader;

import by.iba.student.Repository.GroupRepository;
import by.iba.student.common.Student;

public class StudentLineMapper implements LineMapper<Student> {

    public GroupRepository groupRepository;

    public StudentLineMapper(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    ;

    @Override
    public Student mapLine(String line) {
        String[] data = line.split(";");
        Student student = new Student(data[1], data[2], groupRepository.findGroupById(data[3]));
        student.setId(data[0]);
        return student;
    }
}
