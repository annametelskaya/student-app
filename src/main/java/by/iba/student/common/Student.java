package by.iba.student.common;

public class Student {
    private int id;
    private String firstName;
    private String secondName;
    private Group group;
    private double avgMark;


    public Student() {

    }

    public Student(String firstName, String secondName, Group group) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
        this.avgMark = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGroupNumber() {
        return group.getGroupNumber();
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(String avgMark) {
        this.avgMark = Double.valueOf(avgMark);
    }
}