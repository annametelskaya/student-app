package by.iba.student.common;

public class Professor {
    private int id;
    private String firstName;
    private String secondName;
    private double avgMark;

    public Professor() {
    }

    public Professor(String firstName, String secondName) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.avgMark = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = Integer.valueOf(id);
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

    public void setAvgMark(String avgMark) {
        this.avgMark = Double.valueOf(avgMark);
    }
}
