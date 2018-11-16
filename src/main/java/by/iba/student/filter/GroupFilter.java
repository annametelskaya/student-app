package by.iba.student.filter;

public class GroupFilter {
    private String groupNumber;
    private String avg_mark;

    public GroupFilter() {
        this.groupNumber = "";
        this.avg_mark = "";
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getAvg_mark() {
        return avg_mark;
    }

    public void setAvg_mark(String avg_mark) {
        this.avg_mark = avg_mark;
    }
}
