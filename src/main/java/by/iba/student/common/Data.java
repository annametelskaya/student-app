package by.iba.student.common;

import java.util.*;

public class Data {
    public final static List<Student> STUDENTS = new ArrayList<Student>();
    public final static List<Professor> PROFESSORS = new ArrayList<Professor>();
    public final static List<Marks> MARKS = new ArrayList<Marks>();
    public final static List<Group> GROUP = new ArrayList<Group>();
    public final static List<Subject> SUBJECTS = new ArrayList<Subject>();

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

    public static void addNewStudent(String group, Student student) {
        for (int i = 0; i < GROUP.size(); i++) {
            if (GROUP.get(i).getGroupNumber().equals(group)) {
                GROUP.get(i).addStudent(student);
            }
        }
    }
}