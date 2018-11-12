package by.iba.student.reader;

import by.iba.student.common.Professor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorReader {
    private final String path;

    public ProfessorReader(String path) {
        this.path = path;
    }

    public List<Professor> reader() throws IOException {
        List<Professor> professors = new ArrayList<Professor>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] data = line.split(";");
                Professor professor = new Professor(data[1], data[2], data[3], data[4]);
                professor.setId(data[0]);
                professors.add(professor);
            }

        }
        return professors;
    }
}

