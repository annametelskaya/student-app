package by.iba.student.Repository;

import by.iba.student.common.Data;
import by.iba.student.common.Student;

import java.util.*;

public class StudentRepository {

    public final Map<String, Student> students = new LinkedHashMap<>();
    private GroupRepository groupRepository;

    public StudentRepository(List<Student> students) {
        if (students != null) {
            for (Student student : students) {
                this.students.put(student.getId(), student);
            }
        }
    }

    public Student findStudentById(String id) {
        for (Student student : students.values()) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    public void create(Student student) {
        String id = UUID.randomUUID().toString();
        student.setId(id);
        students.put(id, student);
        //return student;
    }

    public void delete(String id) {
        students.remove(id);
    }

    public Student update(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
}
