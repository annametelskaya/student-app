package by.iba.student.common;

import javax.xml.ws.BindingType;

public class Group {
    private String id;
    private int groupNumber;
    private int number;
    private Student[] students;

    public Group(String n) {
        super();
        this.groupNumber = Integer.decode(n);
        number = 0;
        students = new Student[number];
    }

    public void addStudent(Student student) {
        number++;
        if (number != 1) {
            Student[] save = students;
            students = new Student[number];
            if (number - 1 >= 0) System.arraycopy(save, 0, students, 0, number - 1);
            students[number - 1] = student;
            student.setGroupNumber(number);
        } else {
            students = new Student[number];
            students[number - 1] = student;
            student.setGroupNumber(number);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public String getGroupNumber() {
        return groupNumber + "";
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getNumber() {
        return number + "";
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
