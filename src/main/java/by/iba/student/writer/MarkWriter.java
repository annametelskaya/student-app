package by.iba.student.writer;

import by.iba.student.common.Marks;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MarkWriter {
    private final String path;

    public MarkWriter(String path) {
        this.path = path;
    }

    public void write(List<Marks> marks) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Marks mark : marks) {
                String line;
                if (mark.getComment() != null) {
                    line = String.format("%s;%s;%s;%s;%s;%s;%s",
                            mark.getId(),
                            mark.getSubjectId(),
                            mark.getStudentId(),
                            mark.getProfessorId(),
                            mark.getMark(),
                            mark.getDate(),
                            mark.getComment());
                } else {
                    line = String.format("%s;%s;%s;%s;%s;%s",
                            mark.getId(),
                            mark.getSubjectId(),
                            mark.getStudentId(),
                            mark.getProfessorId(),
                            mark.getMark(),
                            mark.getDate());
                }
                write.write(line);
                write.newLine();
            }
        }
    }
}