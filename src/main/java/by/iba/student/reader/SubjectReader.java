package by.iba.student.reader;

import by.iba.student.common.Data;
import by.iba.student.common.Subject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubjectReader {
    private final String path;

    public SubjectReader(String path) {
        this.path = path;
    }

    public List<Subject> reader() throws IOException {
        List<Subject> subjects = new ArrayList<Subject>();
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] data = line.split(";");
                Subject subject = new Subject(data[1], data[2], data[3]);
                subject.setId(data[0]);
                subjects.add(subject);
            }

        }
        return subjects;
    }
}
