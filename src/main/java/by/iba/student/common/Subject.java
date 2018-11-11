package by.iba.student.common;

public class Subject {
    private String name;
    private String hours;
    private Professor professor;

    public Subject(String name, String hours, Professor professor) {
        this.name = name;
        this.hours = hours;
        this.professor = professor;
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
        return professor.getFirstName() + " " + professor.getSecondName() + " " + professor.getFatherName();
    }


    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
