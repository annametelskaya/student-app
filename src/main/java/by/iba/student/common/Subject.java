package by.iba.student.common;

public class Subject {
    private int id;
    private String name;
    private int hours;
    private Professor professor;
    private double avgMark;

    public Subject() {
    }

    public Subject(String name, int hours, Professor professorId) {
        this.name = name;
        this.hours = hours;
        this.professor = professorId;
        this.avgMark = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getProfessorId() {
        return professor.getId() + "";
    }

    public Professor getProfessor() {
        return professor;
    }

    public String getProfessorName() {
        return professor.getFirstName() + " " + professor.getSecondName();
    }

    public void setProfessorId(Professor professorId) {
        this.professor = professorId;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(String avgMark) {
        this.avgMark = Double.valueOf(avgMark);
    }
}
