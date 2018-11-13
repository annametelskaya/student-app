package by.iba.student.writer;

import by.iba.student.common.Student;
import by.iba.student.common.Subject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SubjectWriter {
    private final String path;

    public SubjectWriter(String path) {
        this.path = path;
    }

    public void write(List<Subject> subjects) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Subject subject : subjects) {
                String line = String.format("%s;%s;%s;%s",
                        subject.getId(),
                        subject.getName(),
                        subject.getHours(),
                        subject.getProfessorId());
                write.write(line);
                write.newLine();
            }
        }
    }
}