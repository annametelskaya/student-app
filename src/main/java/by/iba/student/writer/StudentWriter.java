package by.iba.student.writer;

import by.iba.student.common.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentWriter {
    private final String path;

    public StudentWriter(String path) {
        this.path = path;
    }

    public void write(List<Student> students) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Student st : students) {
                String line = String.format("%s%s%s%s",
                        st.getId(),
                        st.getFirstName(),
                        st.getSecondName(),
                        st.getGroupNumber());
                write.write(line);
                write.newLine();
            }
        }
    }
}
