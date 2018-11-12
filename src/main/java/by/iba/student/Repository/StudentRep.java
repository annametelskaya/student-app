package by.iba.student.Repository;

import by.iba.student.common.Student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRep {

    public final Map<String, Student> students = new LinkedHashMap<>();


    public StudentRep(List<Student> students) {
        if (students != null) {
            for (Student student : students) {
                this.students.put(student.getId(), student);
            }
        }
    }

    public List<Student> findAll() {
        return new ArrayList<>(students.values());
        //new ArrayList<>(STUDENTS);
    }

    public Student create(Student student) {
        String id = UUID.randomUUID().toString();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public void delete(String id) {
        students.remove(id);
    }

    public Student update(Student student) {
        students.put(student.getId(), student);
        return student;
    }
}
