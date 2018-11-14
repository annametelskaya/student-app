package by.iba.student.common;

public class Group {
    private String id;
    private int groupNumber;
    private int number;

    public Group(String n) {
        super();
        this.groupNumber = Integer.valueOf(n);
        number = 0;
    }

    public void addStudent() {
        number++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupNumber() {
        return groupNumber + "";
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getNumber() {
        return number + "";
    }

    public void setNumber(String number) {
        this.number = Integer.valueOf(number);
    }
}
