package by.iba.student.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Marks {
    private int id;
    private Subject subject;
    private Student student;
    private Professor professor;
    private double mark;
    private Date date;
    private String comment;

    public Marks() {
        mark = 0;
        date = new Date();
        comment="";
    }

    public Marks(Subject subject, Student student, Professor professor, double mark, Date date, String comment) {
        super();
        this.subject = subject;
        this.student = student;
        this.professor = professor;
        this.mark = mark;
        this.date = date;
        this.comment = comment;
    }

    public String getSubjectId() {
        return subject.getId() + "";
    }

    public String getSubjectName() {
        return subject.getName();
    }

    public void setSubjectId(Subject subjectId) {
        this.subject = subjectId;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return student.getId() + "";
    }

    public String getStudentName() {
        return student.getFirstName() + " " + student.getSecondName();
    }

    public void setStudentId(Student studentId) {
        this.student = studentId;
    }

    public String getProfessorId() {
        return professor.getId() + "";
    }

    public String getProfessorName() {
        return professor.getFirstName() + " " + professor.getSecondName();
    }

    public void setProfessorId(Professor professorId) {
        this.professor = professorId;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
