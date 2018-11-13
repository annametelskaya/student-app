package by.iba.student.common;

public class Subject {
    private String id;
    private String name;
    private String hours;
    private String professorId;

    public Subject(String name, String hours, String professorId) {
        this.name = name;
        this.hours = hours;
        this.professorId = professorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getProfessorName() {
        return Data.professorRepository.findProfessorById(professorId).getFirstName() + " "
                + Data.professorRepository.findProfessorById(professorId).getSecondName() + " "
                + Data.professorRepository.findProfessorById(professorId).getFatherName();
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessor(String professorId) {
        this.professorId = professorId;
    }
}
