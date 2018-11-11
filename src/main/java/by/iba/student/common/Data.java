package by.iba.student.common;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class Data {
    public final static List<Student> STUDENTS = new ArrayList<Student>();
    public final static List<Professor> PROFESSORS = new ArrayList<Professor>();
    public final static List<Marks> MARKS = new ArrayList<Marks>();
    public final static List<Group> GROUP = new ArrayList<Group>();

    public static Student findStudent(String name, String surname) {
        for (Student student : STUDENTS) {
            if (student.getFirstName().equals(name) && (student.getSecondName().equals(surname))) {
                return student;
            }
        }
        return null;
    }

    public static Professor findProfessor(String name, String surname, String fatherName) {
        for (Professor professor : PROFESSORS) {
            if (professor.getFirstName().equals(name) && professor.getSecondName().equals(surname) && professor.getFatherName().equals(fatherName)) {
                return professor;
            }
        }
        return null;
    }
}
