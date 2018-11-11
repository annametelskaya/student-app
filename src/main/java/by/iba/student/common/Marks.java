package by.iba.student.common;

public class Marks {
    private Student student;
    private Professor professor;
    private String mark;
    private String comment;

    public Marks(Student student, Professor professor, String mark, String comment) {
        super();
        this.student = student;
        this.professor = professor;
        this.mark = mark;
        this.comment = comment;
    }

    public String getStudent() {
        return student.getFirstName() + " " + student.getSecondName();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getProfessor() {
        return professor.getFirstName() + " " + professor.getSecondName() + " " + professor.getFatherName();
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
