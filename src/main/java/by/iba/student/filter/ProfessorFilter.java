package by.iba.student.filter;

public class ProfessorFilter {
    private String firstName;
    private String secondName;

    public ProfessorFilter() {
        firstName = "";
        secondName = "";
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
}