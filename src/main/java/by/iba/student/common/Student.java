package by.iba.student.common;

public class Student {


    private String id;
    private String firstName;
    private String secondName;
    private int groupNumber;


    public Student(String firstName, String secondName, String groupNumber) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.groupNumber = 0;
        this.groupNumber = Integer.valueOf(groupNumber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getGroupNumber() {
        if (groupNumber == 0)
            return "enter group number";
        return groupNumber + "";
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

}