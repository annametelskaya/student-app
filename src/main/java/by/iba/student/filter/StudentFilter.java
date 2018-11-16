package by.iba.student.filter;

public class StudentFilter {
    private String name;
    private String surname;
    private String groupNumber;

    public StudentFilter() {
        name = "";
        surname = "";
        groupNumber = "";
    }

    public StudentFilter(String name, String surname, String groupNumber) {
        this.name = name;
        this.surname = surname;
        this.groupNumber = groupNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
}
