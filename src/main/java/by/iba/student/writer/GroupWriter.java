package by.iba.student.writer;

import by.iba.student.common.Group;
import by.iba.student.common.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GroupWriter {
    private final String path;

    public GroupWriter(String path) {
        this.path = path;
    }

    public void write(List<Group> groups) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Group gr : groups) {
                StringBuilder students= new StringBuilder();
                for (Student st: gr.getStudents()){
                    students.append(String.format(";%s",
                            st.getId()));
                }
                String line = String.format("%s;%s;%s%s",
                        gr.getId(),
                        gr.getGroupNumber(),
                        gr.getNumber(),
                        students);
                write.write(line);
                write.newLine();
            }
        }
    }
}
