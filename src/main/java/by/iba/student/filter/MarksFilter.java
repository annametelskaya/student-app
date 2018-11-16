package by.iba.student.filter;


public class MarksFilter {
    private String professor;
    private String student;
    private String subject;
    private String data;
    private String mark;

    public MarksFilter() {
        professor = "";
        student = "";
        subject = "";
        data = "";
        mark = "";
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
