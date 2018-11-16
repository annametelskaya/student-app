package by.iba.student.filter;

public class SubjectFilter {
    private String name;
    private String hours;
    private String professor;

    public SubjectFilter() {
        name = "";
        hours = "";
        professor = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
