package by.iba.student.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Marks {
    private int id;
    private String subjectId;
    private String studentId;
    private String professorId;
    private double mark;
    private Date date;
    private String comment;

    public Marks(String subjectId, String studentId, String professorId, double mark, Date date, String comment) {
        super();
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.professorId = professorId;
        this.mark = mark;
        this.date = date;
        this.comment = comment;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
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
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
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
