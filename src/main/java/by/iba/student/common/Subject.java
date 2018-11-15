package by.iba.student.common;

public class Subject {
    private int id;
    private String name;
    private int hours;
    private String professorId;
    private double avgMark;

    public Subject(String name, int hours, String professorId) {
        this.name = name;
        this.hours = hours;
        this.professorId = professorId;
        this.avgMark = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(String avgMark) {
        this.avgMark = Double.valueOf(avgMark);
    }
}
