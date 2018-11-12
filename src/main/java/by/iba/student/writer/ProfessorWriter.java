package by.iba.student.writer;

import by.iba.student.common.Professor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProfessorWriter {
    private final String path;

    public ProfessorWriter(String path) {
        this.path = path;
    }

    public void write(List<Professor> professors) throws IOException {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(path))) {
            for (Professor professor : professors) {
                String line = String.format("%s;%s;%s;%s;%s",
                        professor.getId(),
                        professor.getFirstName(),
                        professor.getSecondName(),
                        professor.getFatherName(),
                        professor.getBirthDate());
                write.write(line);
                write.newLine();
            }
        }
    }
}