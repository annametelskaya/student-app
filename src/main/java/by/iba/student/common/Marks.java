package by.iba.student.common;

public class Marks {
    private String id;
    private String studentId;
    private String professorId;
    private String mark;
    private String date;
    private String comment;

    public Marks(String studentId, String professorId, String mark, String date, String comment) {
        super();
        this.studentId = studentId;
        this.professorId = professorId;
        this.mark = mark;
        this.date = date;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

//    public String getStudentName() {
//        return Data.studentRepository.findStudentById(studentId).getFirstName() + " "
//                + Data.studentRepository.findStudentById(studentId).getSecondName();
//    }

//    public String getProfessorName() {
//        return Data.professorRepository.findProfessorById(professorId).getFirstName() + " " +
//                Data.professorRepository.findProfessorById(professorId).getSecondName() + " " +
//                Data.professorRepository.findProfessorById(professorId).getFatherName();
//    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
