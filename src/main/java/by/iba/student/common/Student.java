package by.iba.student.common;

public class Student {


    private String id;
    private String firstName;
    private String secondName;
    private String groupId;


    public Student(String firstName, String secondName, String groupId) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.groupId = groupId;
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

//    public String getStudentGroupNumber() {
//        return Data.groupRepository.findGroupById(groupId).getGroupNumber();
//    }
}