package by.iba.student.common;

public class Student {


    private String id;
    private String firstName;
    private String secondName;
    private Group group;


    public Student(String firstName, String secondName, Group group) {
        super();
        this.firstName = firstName;
        this.secondName = secondName;
        this.group = group;
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
        return group.getId();
    }

    public String getGroupNumber() {
        return group.getGroupNumber();
    }
    public void setGroupId(Group groupId) {
        this.group = groupId;
    }

//    public String getStudentGroupNumber() {
//        return Data.groupRepository.findGroupById(groupId).getGroupNumber();
//    }
}