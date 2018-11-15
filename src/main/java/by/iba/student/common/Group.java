package by.iba.student.common;

public class Group {
    private String groupNumber;
    private double avg_mark;

    public Group(String n) {
        super();
        this.groupNumber = n;
        this.avg_mark = 0;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getAvg_mark() {
        return avg_mark + "";
    }

    public void setAvg_mark(String avg_mark) {
        this.avg_mark = Double.parseDouble(avg_mark);
    }
}
