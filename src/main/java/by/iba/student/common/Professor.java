package by.iba.student.common;

public class Professor {
    private int id;
    private String firstName;
    private String secondName;
    private double avgMark;

    public Professor(String firstName, String secondName) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.avgMark = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}
