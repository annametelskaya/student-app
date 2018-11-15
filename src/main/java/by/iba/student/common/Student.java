package by.iba.student.common;

public class Student {
    private int id;
    private String firstName;
    private String secondName;
    private String group;
    private double avgMark;


    public Student(String firstName, String secondName, String group) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
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

    public String getGroup() {
        return group;
    }

    public void setGroupId(String groupId) {
        this.group = groupId;
    }

    public double getAvgMark() {
        return avgMark;
    }

    public void setAvgMark(double avgMark) {
        this.avgMark = avgMark;
    }
}