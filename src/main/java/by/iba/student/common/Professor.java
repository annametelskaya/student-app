package by.iba.student.common;

public class Professor {
    private String id;
    private String firstName;
    private String secondName;
    private String fatherName;
    private String birthDate;

    public Professor(String firstName, String secondName, String fatherName, String birthDate) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
